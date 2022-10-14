package com.artzvrzn.store.catalogue.mapper;

import com.artzvrzn.store.catalogue.dao.api.CategoryRepository;
import com.artzvrzn.store.catalogue.domain.Category;
import com.artzvrzn.store.catalogue.domain.Item;
import com.artzvrzn.store.catalogue.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class ItemMapper {
  @Autowired
  private CategoryRepository categoryRepository;

  public Item toEntity(ItemDto source) {
    Category category = categoryRepository.getReferenceById(source.getCategory());
    return Item.getBuilder()
      .name(source.getName())
      .description(source.getDescription())
      .brand(source.getBrand())
      .image(source.getImage())
      .price(source.getPrice())
      .category(category)
      .build();
  }

  public Item toEntity(Item target, ItemDto source) {
    Category category = categoryRepository.getReferenceById(source.getCategory());
    target.setName(source.getName());
    target.setDescription(source.getDescription());
    target.setBrand(source.getBrand());
    target.setImage(source.getImage());
    target.setPrice(source.getPrice());
    target.setCategory(category);
    return target;
  }

  @Mapping(target = "category", source = "source.category.id")
  public abstract ItemDto toDto(Item source);
}
