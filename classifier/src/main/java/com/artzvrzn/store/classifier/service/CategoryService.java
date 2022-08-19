package com.artzvrzn.store.classifier.service;

import com.artzvrzn.store.classifier.dao.api.CategoryRepository;
import com.artzvrzn.store.classifier.dao.entity.CategoryEntity;
import com.artzvrzn.store.classifier.model.Category;
import com.artzvrzn.store.classifier.service.api.ICategoryService;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.artzvrzn.store.classifier.model.constant.BasicMessages.*;

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
  public Page<Category> getPage(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
    return repository.findAll(pageable).map(c -> cs.convert(c, Category.class));
  }

  @Override
  public Page<Category> getSubcategories(UUID parentId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
    return repository
        .findAllByParentCategory_Id(parentId, pageable).map(c -> cs.convert(c, Category.class));
  }

  @Override
  public Category create(Category dto) {
    if (dto == null) {
      throw new IllegalArgumentException(ERROR_CATEGORY_NOT_PASSED.getMessage());
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
    if (!entity.getUpdated().isEqual(updated)) {
      throw new IllegalArgumentException(ERROR_RECORD_UPDATED.getMessage());
    }
    dto.setUpdated(LocalDateTime.now(ZoneOffset.UTC));
    entity = cs.convert(dto, CategoryEntity.class);
    if (entity == null) {
      throw new IllegalStateException(ERROR_NULL_CONVERSION.getMessage());
    }
    repository.save(entity);
    log.debug(LOG_UPDATED.toString(), DTO_NAME, dto.getId());
    return dto;
  }

  @Override
  public void delete(UUID id, LocalDateTime updated) {
    CategoryEntity entity = CrudUtils.getEntityOrThrow(id, repository);
    if (!entity.getUpdated().isEqual(updated)) {
      throw new IllegalArgumentException(ERROR_RECORD_UPDATED.getMessage());
    }
    repository.delete(entity);
    log.debug(LOG_DELETED.toString(), DTO_NAME, entity.getId());
  }
}
