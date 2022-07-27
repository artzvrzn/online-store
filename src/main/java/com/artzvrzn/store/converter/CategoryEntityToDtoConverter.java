package com.artzvrzn.store.converter;

import com.artzvrzn.store.dao.entity.CategoryEntity;
import com.artzvrzn.store.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryEntityToDtoConverter implements Converter<CategoryEntity, Category> {

  @Override
  public Category convert(CategoryEntity entity) {
    Category dto = new Category();
    dto.setId(entity.getId());
    dto.setCreated(entity.getCreated());
    dto.setUpdated(entity.getUpdated());
    dto.setName(entity.getName());
    return dto;
  }
}
