package com.artzvrzn.store.view;

import com.artzvrzn.store.dao.api.CategoryRepository;
import com.artzvrzn.store.dao.entity.CategoryEntity;
import com.artzvrzn.store.model.Category;
import com.artzvrzn.store.view.api.CRUDService;
import com.artzvrzn.store.view.api.ICategoryService;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends CRUDService<CategoryEntity> implements ICategoryService {

  public CategoryService(ConversionService conversionService, CategoryRepository repository) {
    super(conversionService, repository);
  }

  @Override
  public Category get(UUID id) {
    CategoryEntity entity = getEntityOrThrow(id);
    return convertOrThrow(entity, Category.class);
  }

  @Override
  public Page<Category> get(int page, int size) {
    Pageable request = PageRequest.of(page, size, Sort.by("name").ascending());
    return repository.findAll(request).map(e -> conversionService.convert(e, Category.class));
  }

  @Override
  public Category create(Category category) {
    category = assignIdAndTime(category);
    CategoryEntity entity = convertOrThrow(category, CategoryEntity.class);
    repository.save(entity);
    return category;
  }

  @Override
  public Category update(UUID id, LocalDateTime updated, Category category) {
    CategoryEntity entity = getEntityOrThrow(id, updated);
    entity.setName(category.getName());
    entity.setUpdated(LocalDateTime.now(ZoneOffset.UTC));
    repository.save(entity);
    return conversionService.convert(entity, Category.class);
  }

  @Override
  public void delete(UUID id, LocalDateTime updated) {
    CategoryEntity entity = getEntityOrThrow(id, updated);
    repository.delete(entity);
  }
}
