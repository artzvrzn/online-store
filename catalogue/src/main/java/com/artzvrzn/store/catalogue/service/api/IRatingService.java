package com.artzvrzn.store.catalogue.service.api;

import com.artzvrzn.store.catalogue.model.Grade;
import com.artzvrzn.store.catalogue.model.Rating;
import java.util.List;
import java.util.UUID;

public interface IRatingService {

  void vote(Grade grade, UUID userId, UUID itemId);

  Rating get(UUID userId, UUID itemId);

  List<Rating> getAllByUser(UUID userId);

  List<Rating> getAllByItem(UUID itemId);
}
