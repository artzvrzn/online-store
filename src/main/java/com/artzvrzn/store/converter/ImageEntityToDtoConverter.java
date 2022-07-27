package com.artzvrzn.store.converter;

import com.artzvrzn.store.dao.entity.ImageEntity;
import com.artzvrzn.store.model.Image;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ImageEntityToDtoConverter implements Converter<ImageEntity, Image> {

  @Override
  public Image convert(ImageEntity entity) {
    Image dto = new Image();
    dto.setId(entity.getId());
    dto.setCreated(entity.getCreated());
    dto.setUpdated(entity.getUpdated());
    dto.setUrl(entity.getUrl());
    return dto;
  }
}
