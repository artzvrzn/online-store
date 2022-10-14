package com.artzvrzn.store.catalogue.service;

import static com.artzvrzn.store.catalogue.domain.constant.CommonMessage.*;

import com.artzvrzn.store.catalogue.dao.api.ItemRepository;
import com.artzvrzn.store.catalogue.dao.api.RatingRepository;
import com.artzvrzn.store.catalogue.domain.Item;
import com.artzvrzn.store.catalogue.domain.Rating;
import com.artzvrzn.store.catalogue.dto.RatingDto;
import com.artzvrzn.store.catalogue.mapper.RatingMapper;
import com.artzvrzn.store.catalogue.service.api.RatingService;
import com.artzvrzn.store.catalogue.service.api.UserService;
import com.artzvrzn.store.catalogue.service.util.CrudUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
  private final ItemRepository itemRepository;
  private final RatingRepository ratingRepository;
  private final UserService userService;
  private final RatingMapper ratingMapper;

  @Override
  public void vote(RatingDto request) {
    UUID userId = userService.getAuthenticatedUser().getUserId();
    Optional<Rating> optionalRating =
      ratingRepository.findByUserIdAndItem_Id(userId, request.getItemId());
    Rating rating;
    if (optionalRating.isPresent()) {
      rating = optionalRating.get();
      rating.setGrade(request.getGrade());
    } else {
      Item item = itemRepository.getReferenceById(request.getItemId());
      rating = Rating.getBuilder()
        .grade(request.getGrade())
        .userId(userId)
        .item(item)
        .build();
    }
    ratingRepository.save(rating);
    log.debug("User {} has voted for item {} with grade {}",
      userId, request.getItemId(), request.getGrade());
  }

  @Override
  public RatingDto get(Long id) {
    return ratingMapper.toDto(CrudUtils.getEntityOrThrow(id, ratingRepository));
  }

  @Override
  public RatingDto get(UUID userId, UUID itemId) {
    return ratingMapper.toDto(getEntityOrThrow(userId, itemId));
  }

  @Override
  public List<RatingDto> getAllByUser(UUID userId) {
    final Sort sort = Sort.by("updated");
    return ratingRepository.findAllByUserId(userId, sort).stream()
      .map(ratingMapper::toDto)
      .collect(Collectors.toList());
  }

  @Override
  public List<RatingDto> getAllByItem(UUID itemId) {
    final Sort sort = Sort.by("updated");
    return ratingRepository.findAllByItem_Id(itemId, sort).stream()
      .map(ratingMapper::toDto)
      .collect(Collectors.toList());
  }


  private Rating getEntityOrThrow(UUID userId, UUID itemId) {
    final String error = "User %s didn't vote for item %s yet";
    return ratingRepository.findByUserIdAndItem_Id(userId, itemId).orElseThrow(
      () -> new IllegalArgumentException(String.format(error, userId, itemId)));
  }
}
