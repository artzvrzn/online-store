package com.artzvrzn.store.catalogue.config.mapper.converter;

import com.artzvrzn.store.catalogue.domain.Category;
import java.util.UUID;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class CategoryToUUIDConverter implements Converter<Category, UUID> {

  @Override
  public UUID convert(MappingContext<Category, UUID> context) {
    return context.getSource() == null ? null : context.getSource().getId();
  }
}
