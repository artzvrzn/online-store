package com.artzvrzn.store.catalogue.service;

import com.artzvrzn.store.catalogue.dao.api.ItemRepository;
import com.artzvrzn.store.catalogue.dao.api.RatingRepository;
import com.artzvrzn.store.catalogue.dao.entity.RatingEntity;
import com.artzvrzn.store.catalogue.model.Grade;
import com.artzvrzn.store.catalogue.model.Rating;
import com.artzvrzn.store.catalogue.service.api.IRatingService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService implements IRatingService {

  @Autowired
  private RatingRepository ratingRepository;
  @Autowired
  private ItemRepository itemRepository;
  @Autowired
  private ModelMapper mapper;

  @Override
  public void vote(Grade grade, UUID userId, UUID itemId) {
    RatingEntity ratingEntity = new RatingEntity();
    ratingEntity.setGrade(grade);
    ratingEntity.setUserId(userId);
    ratingEntity.setItem(itemRepository.getReferenceById(itemId));
    ratingRepository.save(ratingEntity);
  }

  @Override
  public Rating get(UUID userId, UUID itemId) {
    RatingEntity entity = ratingRepository.findByUserIdAndItem_Id(userId, itemId);
    return mapper.map(entity, Rating.class);
  }

  @Override
  public List<Rating> getAllByUser(UUID userId) {
    return ratingRepository.findAllByUserId(userId)
        .stream()
        .map(e -> mapper.map(e, Rating.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<Rating> getAllByItem(UUID itemId) {
    return ratingRepository.findAllByItem_Id(itemId)
        .stream()
        .map(e -> mapper.map(e, Rating.class))
        .collect(Collectors.toList());
  }

  private RatingEntity getEntityOrThrow(Long id) {
    Optional<RatingEntity> optionalEntity = ratingRepository.findById(id);
    if (optionalEntity.isEmpty()) {
      throw new IllegalArgumentException("doesn't exist");
    }
    return optionalEntity.get();
  }
}
