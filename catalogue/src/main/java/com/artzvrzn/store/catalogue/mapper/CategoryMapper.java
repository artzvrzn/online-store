package com.artzvrzn.store.catalogue.mapper;

import com.artzvrzn.store.catalogue.domain.Category;
import com.artzvrzn.store.catalogue.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {

  @Mapping(target = "parentCategory", source = "source.parentCategory.id")
  CategoryDto toDto(Category source);
}
