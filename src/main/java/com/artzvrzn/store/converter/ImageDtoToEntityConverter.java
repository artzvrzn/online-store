package com.artzvrzn.store.converter;

import com.artzvrzn.store.dao.entity.ImageEntity;
import com.artzvrzn.store.model.Image;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ImageDtoToEntityConverter implements Converter<Image, ImageEntity> {

  @Override
  public ImageEntity convert(Image dto) {
    ImageEntity entity = new ImageEntity();
    entity.setId(dto.getId());
    entity.setCreated(dto.getCreated());
    entity.setUpdated(dto.getUpdated());
    entity.setUrl(dto.getUrl());
    return entity;
  }
}
