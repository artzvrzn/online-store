package com.artzvrzn.store.converter;

import com.artzvrzn.store.dao.entity.ProductEntity;
import com.artzvrzn.store.model.Category;
import com.artzvrzn.store.model.Image;
import com.artzvrzn.store.model.Product;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToDtoConverter implements Converter<ProductEntity, Product> {

  @Autowired
  private ConversionService conversionService;

  @Override
  public Product convert(ProductEntity entity) {
    Product dto = new Product();
    dto.setId(entity.getId());
    dto.setCreated(entity.getCreated());
    dto.setUpdated(entity.getUpdated());
    dto.setName(entity.getName());
    dto.setDescription(entity.getDescription());
    dto.setPrice(entity.getPrice());
    dto.setCategory(conversionService.convert(entity.getCategory(), Category.class));
    dto.setCover(conversionService.convert(entity.getCover(), Image.class));
    dto.setImages(entity.getImages()
        .stream()
        .map(image -> conversionService.convert(image, Image.class))
        .collect(Collectors.toSet()));
    return dto;
  }
}
