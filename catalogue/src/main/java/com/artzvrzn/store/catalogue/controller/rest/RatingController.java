package com.artzvrzn.store.catalogue.controller.rest;

import com.artzvrzn.store.catalogue.model.Rating;
import com.artzvrzn.store.catalogue.service.api.IRatingService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/rating")
public class RatingController {

  @Autowired
  private IRatingService ratingService;

  @PostMapping(value = {"/item", "/item/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void vote(@RequestBody Rating rating) {
    ratingService.vote(rating.getGrade(), rating.getUser(), rating.getItem());
  }

  @GetMapping(value = {"/user/{id}", "/user/{id}/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<Rating> getByUser(@PathVariable("id") UUID userId) {
    return ratingService.getAllByUser(userId);
  }

  @GetMapping(value = {"/item/{id}", "/item/{id}/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<Rating> getByItem(@PathVariable("id") UUID itemId) {
    return ratingService.getAllByItem(itemId);
  }
}
