package com.artzvrzn.store.catalogue.controller.rest;

import com.artzvrzn.store.catalogue.dto.RatingDto;
import com.artzvrzn.store.catalogue.service.api.RatingService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {
  @Autowired
  private RatingService ratingService;

  @PostMapping(
    value = {"/item/{itemId}", "/item/{itemId}/"},
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public void vote(@RequestBody RatingDto request, @PathVariable("itemId") UUID itemId) {
    request.setItemId(itemId);
    ratingService.vote(request);
  }

  @GetMapping(value = {"/{id}", "/{id}/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public RatingDto get(@PathVariable("id") Long id) {
    return ratingService.get(id);
  }

  @GetMapping(
    value = {"/item/{itemId}", "/item/{itemId}/"},
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public List<RatingDto> getAllByItem(@PathVariable("itemId") UUID itemId) {
    return ratingService.getAllByItem(itemId);
  }

  @GetMapping(
    value = {"/item/{itemId}/user/{userId}", "/item/{itemId}/user/{userId}/"},
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public RatingDto get(@PathVariable("itemId") UUID itemId, @PathVariable("userId") UUID userId) {
    return ratingService.get(userId, itemId);
  }

  @GetMapping(
    value = {"/user/{userId}", "/user/{userId}/"},
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public List<RatingDto> getAllByUser(@PathVariable("userId") UUID userId) {
    return ratingService.getAllByUser(userId);
  }
}
