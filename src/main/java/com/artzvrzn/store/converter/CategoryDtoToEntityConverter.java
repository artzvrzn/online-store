package com.artzvrzn.store.converter;

import com.artzvrzn.store.dao.entity.CategoryEntity;
import com.artzvrzn.store.model.Category;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToEntityConverter implements Converter<Category, CategoryEntity> {

  @Autowired
  private ConversionService cs;

  @Override
  public CategoryEntity convert(Category dto) {
    CategoryEntity entity = new CategoryEntity();
    entity.setId(dto.getId());
    entity.setCreated(dto.getCreated());
    entity.setUpdated(dto.getUpdated());
    entity.setName(dto.getName());
    if (dto.getParentCategory() != null) {
      entity.setParentCategory(cs.convert(dto.getParentCategory(), CategoryEntity.class));
    }
    entity.setSubcategories(
        dto.getSubcategories()
            .stream()
            .filter(Objects::nonNull)
            .map(c -> cs.convert(c, CategoryEntity.class))
            .collect(Collectors.toList()));
    return entity;
  }
}
