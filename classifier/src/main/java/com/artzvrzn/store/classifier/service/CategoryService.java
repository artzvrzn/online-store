package com.artzvrzn.store.classifier.service;

import com.artzvrzn.store.classifier.dao.api.CategoryRepository;
import com.artzvrzn.store.classifier.dao.entity.CategoryEntity;
import com.artzvrzn.store.classifier.exception.RecordAlreadyExist;
import com.artzvrzn.store.classifier.model.Category;
import com.artzvrzn.store.classifier.service.api.ICategoryService;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.artzvrzn.store.classifier.model.constant.CommonMessage.*;

@Service
@Transactional(rollbackOn = Exception.class)
@Log4j2
public class CategoryService implements ICategoryService {
  private static final String DTO_NAME = "category";
  @Autowired
  private CategoryRepository repository;
  @Autowired
  private ConversionService cs;

  @Override
  public Category get(UUID id) {
    return cs.convert(CrudUtils.getEntityOrThrow(id, repository), Category.class);
  }

  @Override
  public List<Category> getAll() {
    return repository.findAll(Sort.by("name"))
        .stream()
        .map(c -> cs.convert(c, Category.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<Category> getTopLevelCategories() {
    return repository.findAllByParentCategory_Id(null, Sort.by("name"))
        .stream()
        .map(c -> cs.convert(c, Category.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<Category> getDirectSubcategories(UUID parentId) {
    return repository.findAllByParentCategory_Id(parentId, Sort.by("name"))
        .stream()
        .map(c -> cs.convert(c, Category.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<Category> getIndirectSubcategories(UUID parentId) {
    CategoryEntity entity = CrudUtils.getEntityOrThrow(parentId, repository);
    Set<CategoryEntity> descendants = new HashSet<>();
    if (entity.getSubcategories().isEmpty()) {
      return Collections.emptyList();
    }
    preorderTraversal(entity, descendants);
    return descendants
        .stream()
        .filter(c -> !c.equals(entity))
        .map(c -> cs.convert(c, Category.class))
        .collect(Collectors.toList());
  }

  @Override
  public boolean isExist(UUID id) {
    return repository.existsById(id);
  }

  private void preorderTraversal(CategoryEntity entity, Set<CategoryEntity> descendants) {
    if (entity.getParentCategory() != null) {
      descendants.add(entity);
    }
    for (CategoryEntity subEntity: entity.getSubcategories()) {
      preorderTraversal(subEntity, descendants);
    }
  }

  @Override
  public Category create(Category dto) {
    if (dto == null) {
      throw new IllegalArgumentException(ERROR_CATEGORY_NOT_PASSED.getMessage());
    }
    if (repository.existsByName(dto.getName())) {
      throw new RecordAlreadyExist(ERROR_RECORD_EXIST.getMessage());
    }
    CrudUtils.provideCreationTime(dto);
    dto.setId(UUID.randomUUID());
    CategoryEntity entity = cs.convert(dto, CategoryEntity.class);
    if (entity == null) {
      throw new IllegalStateException(ERROR_NULL_CONVERSION.getMessage());
    }
    repository.save(entity);
    log.debug(LOG_CREATED.toString(), DTO_NAME, dto.getId());
    return dto;
  }

  @Override
  public Category createSubcategory(Category dto, UUID parentId) {
    if (dto == null) {
      throw new IllegalArgumentException(ERROR_CATEGORY_NOT_PASSED.getMessage());
    }
    if (repository.existsByName(dto.getName())) {
      throw new RecordAlreadyExist(ERROR_RECORD_EXIST.getMessage());
    }
    CrudUtils.provideCreationTime(dto);
    dto.setId(UUID.randomUUID());
    dto.setParentCategory(parentId);
    CategoryEntity entity = cs.convert(dto, CategoryEntity.class);
    if (entity == null) {
      throw new IllegalStateException(ERROR_NULL_CONVERSION.getMessage());
    }
    repository.save(entity);
    log.debug(LOG_CREATED.toString(), DTO_NAME, dto.getId());
    return dto;
  }

  @Override
  public Category update(Category dto, UUID id, LocalDateTime updated) {
    CategoryEntity entity = CrudUtils.getEntityOrThrow(id, repository);
    CrudUtils.optimisticBlockCheck(entity, updated);
    checkParentCategoryCircularDependency(dto.getParentCategory(), entity);
    entity.setUpdated(LocalDateTime.now(ZoneOffset.UTC));
    entity.setName(dto.getName());
    repository.save(entity);
    log.debug(LOG_UPDATED.toString(), DTO_NAME, dto.getId());
    return cs.convert(entity, Category.class);
  }

  @Override
  public void delete(UUID id, LocalDateTime updated) {
    CategoryEntity entity = CrudUtils.getEntityOrThrow(id, repository);
    CrudUtils.optimisticBlockCheck(entity, updated);
    repository.delete(entity);
    log.debug(LOG_DELETED.toString(), DTO_NAME, entity.getId());
  }

  private void checkParentCategoryCircularDependency(
      UUID candidateId, CategoryEntity entity
  ) {
    if (candidateId == null) {
      return;
    }
    CategoryEntity parentCandidate =
        CrudUtils.getEntityOrThrow(candidateId, repository);
    if (!isDescendant(entity.getId(), parentCandidate)) {
      entity.setParentCategory(parentCandidate);
    } else {
      throw new IllegalArgumentException(ERROR_CATEGORY_PARENT_CHILD.getMessage());
    }
    if (candidateId.equals(entity.getId())) {
      throw new IllegalArgumentException(ERROR_CATEGORY_PARENT_SELF.getMessage());
    }
  }

  private static boolean isDescendant(UUID id, CategoryEntity entity) {
    if (entity == null) return false;
    if (entity.getId().equals(id)) return true;
    return isDescendant(id, entity.getParentCategory());
  }
}
