package com.artzvrzn.store.classifier.converter;

import com.artzvrzn.store.classifier.dao.api.CategoryRepository;
import com.artzvrzn.store.classifier.dao.entity.CategoryEntity;
import com.artzvrzn.store.classifier.model.Category;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToEntityConverter implements Converter<Category, CategoryEntity> {

  @Autowired
  private CategoryRepository repository;

  @Override
  public CategoryEntity convert(Category dto) {
    CategoryEntity entity = new CategoryEntity();
    entity.setId(dto.getId());
    entity.setCreated(dto.getCreated());
    entity.setUpdated(dto.getUpdated());
    entity.setName(dto.getName());
    if (dto.getParentCategory() != null) {
      Optional<CategoryEntity> optional = repository.findById(dto.getParentCategory());
      if (optional.isEmpty()) {
        throw new IllegalArgumentException("parent category doesn't exist");
      }
      entity.setParentCategory(optional.get());
    }
    return entity;
  }
}
