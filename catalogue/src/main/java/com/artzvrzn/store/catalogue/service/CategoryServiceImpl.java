package com.artzvrzn.store.catalogue.service;

import static com.artzvrzn.store.catalogue.domain.constant.CommonMessage.*;

import com.artzvrzn.store.catalogue.dao.api.CategoryRepository;
import com.artzvrzn.store.catalogue.domain.Category;
import com.artzvrzn.store.catalogue.dto.request.CategoryRequest;
import com.artzvrzn.store.catalogue.dto.response.CategoryResponse;
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
  private final ModelMapper mapper;

  @Override
  public CategoryResponse get(UUID id) {
    return mapper.map(CrudUtils.getEntityOrThrow(id, repository), CategoryResponse.class);
  }

  @Override
  public List<CategoryResponse> getAll() {
    return repository.findAll(Sort.by("name"))
        .stream()
        .map(e -> mapper.map(e, CategoryResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<CategoryResponse> getTopLevelCategories() {
    return repository.findAllByParentCategory_Id(null, Sort.by("name"))
        .stream()
        .map(e -> mapper.map(e, CategoryResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<CategoryResponse> getDirectSubcategories(UUID parentId) {
    return repository.findAllByParentCategory_Id(parentId, Sort.by("name"))
        .stream()
        .map(e -> mapper.map(e, CategoryResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  //TODO Temporal solution. (Makes queries recursively)
  public List<CategoryResponse> getIndirectSubcategories(UUID parentId) {
    Category parentCategory = CrudUtils.getEntityOrThrow(parentId, repository);
    if (parentCategory.getSubcategories().isEmpty()) {
      return Collections.emptyList();
    }
    return getAllDescendants(parentCategory)
        .stream()
        .filter(c -> !c.getId().equals(parentCategory.getId()))
        .map(e -> mapper.map(e, CategoryResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public boolean isExist(UUID id) {
    return repository.existsById(id);
  }

  @Override
  @Transactional
  public CategoryResponse create(CategoryRequest request) {
    validateRequest(request);
    Category parentCategory = request.getParentCategory() == null ? null :
      CrudUtils.getEntityOrThrow(request.getParentCategory(), repository);
    Category category = Category.builder()
      .withName(request.getName())
      .withParentCategory(parentCategory)
      .build();
    category = repository.save(category);
    log.debug(LOG_CREATED.toString(), ENTITY_NAME, category.getId());
    return mapper.map(category, CategoryResponse.class);
  }

  @Override
  @Transactional
  public CategoryResponse update(CategoryRequest request, UUID id) {
    Category parentCategory = null;
    if (request.getParentCategory() != null) {
      parentCategory = CrudUtils.getEntityOrThrow(request.getParentCategory(), repository);
      if (isDescendant(id, parentCategory)) {
        throw new IllegalArgumentException("Circular dependency");
      }
    }
    Category category = CrudUtils.getEntityOrThrow(id, repository);
    category.setName(category.getName());
    category.setParentCategory(parentCategory);
    category = repository.save(category);
    log.debug(LOG_UPDATED.toString(), ENTITY_NAME, id);
    return mapper.map(category, CategoryResponse.class);
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

  private void validateRequest(CategoryRequest request) {
    if (request == null) {
      throw new IllegalArgumentException(ERROR_CATEGORY_NOT_PASSED.getMessage());
    }
    if (repository.existsByName(request.getName())) {
      throw new IllegalArgumentException(ERROR_RECORD_EXIST.getMessage());
    }
  }
}
