package com.artzvrzn.store.catalogue.service;

import static com.artzvrzn.store.catalogue.domain.constant.CommonMessage.*;

import com.artzvrzn.store.catalogue.dao.api.CategoryRepository;
import com.artzvrzn.store.catalogue.domain.Category;
import com.artzvrzn.store.catalogue.dto.CategoryDto;
import com.artzvrzn.store.catalogue.mapper.CategoryMapper;
import com.artzvrzn.store.catalogue.service.api.CategoryService;
import com.artzvrzn.store.catalogue.service.util.CrudUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private static final String ENTITY_NAME = "category";
  private final CategoryRepository repository;
  private final CategoryMapper mapper;
  @Override
  public CategoryDto get(UUID id) {
    return mapper.toDto(CrudUtils.getEntityOrThrow(id, repository));
  }

  @Override
  public List<CategoryDto> getAll() {
    return repository.findAll(Sort.by("name"))
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<CategoryDto> getTopLevelCategories() {
    return repository.findAllByParentCategory_Id(null, Sort.by("name"))
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<CategoryDto> getDirectSubcategories(UUID parentId) {
    return repository.findAllByParentCategory_Id(parentId, Sort.by("name"))
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  //TODO Temporal solution. (Makes queries recursively)
  public List<CategoryDto> getIndirectSubcategories(UUID parentId) {
    Category parentCategory = CrudUtils.getEntityOrThrow(parentId, repository);
    if (parentCategory.getSubcategories().isEmpty()) {
      return Collections.emptyList();
    }
    return getAllDescendants(parentCategory)
        .stream()
        .filter(c -> !c.getId().equals(parentCategory.getId()))
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public boolean isExist(UUID id) {
    return repository.existsById(id);
  }

  @Override
  @Transactional
  public CategoryDto create(CategoryDto dto) {
    validateRequest(dto);
    Category parentCategory = dto.getParentCategory() == null ? null :
      CrudUtils.getEntityOrThrow(dto.getParentCategory(), repository);
    Category category = Category.builder()
      .withName(dto.getName())
      .withParentCategory(parentCategory)
      .build();
    category = repository.save(category);
    log.debug(LOG_CREATED.toString(), ENTITY_NAME, category.getId());
    return mapper.toDto(category);
  }

  @Override
  @Transactional
  public CategoryDto update(CategoryDto dto, UUID id) {
    Category parentCategory = null;
    if (dto.getParentCategory() != null) {
      parentCategory = CrudUtils.getEntityOrThrow(dto.getParentCategory(), repository);
      if (isDescendant(id, parentCategory)) {
        throw new IllegalArgumentException("Circular dependency");
      }
    }
    Category category = CrudUtils.getEntityOrThrow(id, repository);
    category.setName(category.getName());
    category.setParentCategory(parentCategory);
    category = repository.save(category);
    log.debug(LOG_UPDATED.toString(), ENTITY_NAME, id);
    return mapper.toDto(category);
  }

  @Override
  public void delete(UUID id) {
    repository.deleteById(id);
    log.debug(LOG_DELETED.toString(), ENTITY_NAME, id);
  }

  private Set<Category> getAllDescendants(Category category) {
    Set<Category> descendants = new HashSet<>();
    preorderTraversal(category, descendants);
    return descendants;
  }

  private void preorderTraversal(Category category, Set<Category> descendants) {
    if (category.getParentCategory() != null) {
      descendants.add(category);
    }
    for (Category subCategory : category.getSubcategories()) {
      preorderTraversal(subCategory, descendants);
    }
  }

  private boolean isDescendant(UUID id, Category category) {
    if (category == null) return false;
    if (category.getId().equals(id)) return true;
    return isDescendant(id, category.getParentCategory());
  }

  private void validateRequest(CategoryDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException(ERROR_CATEGORY_NOT_PASSED.getMessage());
    }
    if (repository.existsByName(dto.getName())) {
      throw new IllegalArgumentException(ERROR_RECORD_EXIST.getMessage());
    }
  }
}
