package com.artzvrzn.store.catalogue.config.mapper;

import com.artzvrzn.store.catalogue.config.mapper.converter.CategoryToUUIDConverter;
import com.artzvrzn.store.catalogue.domain.Category;
import com.artzvrzn.store.catalogue.dto.request.CategoryRequest;
import com.artzvrzn.store.catalogue.dto.response.CategoryResponse;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper getMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.addConverter(new CategoryToUUIDConverter());
    return modelMapper;
  }
}
