package com.artzvrzn.store.catalogue.service.api;

import com.artzvrzn.store.catalogue.dto.request.RatingRequest;
import com.artzvrzn.store.catalogue.dto.response.RatingResponse;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface RatingService {

  void vote(RatingRequest request, UUID itemId);

  RatingResponse get(UUID userId, UUID itemId);

  Page<RatingResponse> getAllByUser(UUID userId);

  Page<RatingResponse> getAllByItem(UUID itemId);
}
