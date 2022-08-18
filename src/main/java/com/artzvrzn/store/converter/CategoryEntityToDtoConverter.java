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
public class CategoryEntityToDtoConverter implements Converter<CategoryEntity, Category> {

  @Autowired
  private ConversionService cs;

  @Override
  public Category convert(CategoryEntity entity) {
    Category dto = new Category();
    dto.setId(entity.getId());
    dto.setCreated(entity.getCreated());
    dto.setUpdated(entity.getUpdated());
    dto.setName(entity.getName());
    if (entity.getParentCategory() != null) {
      dto.setParentCategory(cs.convert(entity.getParentCategory(), Category.class));
    }
    dto.setSubcategories(
        dto.getSubcategories()
            .stream()
            .filter(Objects::nonNull)
            .map(c -> cs.convert(c, Category.class))
            .collect(Collectors.toList()));
    return dto;
  }
}
