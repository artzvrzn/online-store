package com.artzvrzn.store.converter;

import com.artzvrzn.store.dao.entity.CategoryEntity;
import com.artzvrzn.store.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToEntityConverter implements Converter<Category, CategoryEntity> {

  @Override
  public CategoryEntity convert(Category dto) {
    CategoryEntity entity = new CategoryEntity();
    entity.setId(dto.getId());
    entity.setCreated(dto.getCreated());
    entity.setUpdated(dto.getUpdated());
    entity.setName(dto.getName());
    return entity;
  }
}
