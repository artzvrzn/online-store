package com.artzvrzn.store.converter;

import com.artzvrzn.store.dao.entity.CategoryEntity;
import com.artzvrzn.store.dao.entity.ImageEntity;
import com.artzvrzn.store.dao.entity.ProductEntity;
import com.artzvrzn.store.model.Product;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToEntityConverter implements Converter<Product, ProductEntity> {

  @Autowired
  private ConversionService conversionService;

  @Override
  public ProductEntity convert(Product dto) {
    ProductEntity entity = new ProductEntity();
    entity.setId(dto.getId());
    entity.setCreated(dto.getCreated());
    entity.setUpdated(dto.getUpdated());
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setPrice(dto.getPrice());
    entity.setCategory(conversionService.convert(dto.getCategory(), CategoryEntity.class));
    entity.setCover(conversionService.convert(dto.getCover(), ImageEntity.class));
    entity.setImages(dto.getImages()
        .stream()
        .map(image -> conversionService.convert(image, ImageEntity.class))
        .collect(Collectors.toSet()));
    return entity;
  }
}
