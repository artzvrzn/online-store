package com.artzvrzn.store.catalogue.mapper;

import com.artzvrzn.store.catalogue.dao.api.ItemRepository;
import com.artzvrzn.store.catalogue.domain.Item;
import com.artzvrzn.store.catalogue.domain.Rating;
import com.artzvrzn.store.catalogue.dto.RatingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RatingMapper {

  @Mapping(target = "itemId", source = "source.item.id")
  RatingDto toDto(Rating source);
}
