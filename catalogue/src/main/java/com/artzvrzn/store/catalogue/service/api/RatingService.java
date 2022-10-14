package com.artzvrzn.store.catalogue.service.api;

import com.artzvrzn.store.catalogue.dto.RatingDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface RatingService {

  void vote(RatingDto dto);

  RatingDto get(Long id);

  RatingDto get(UUID userId, UUID itemId);

  List<RatingDto> getAllByUser(UUID userId);

  List<RatingDto> getAllByItem(UUID itemId);
}
