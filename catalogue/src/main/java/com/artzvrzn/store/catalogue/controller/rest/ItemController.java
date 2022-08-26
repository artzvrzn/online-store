package com.artzvrzn.store.catalogue.controller.rest;

import com.artzvrzn.store.catalogue.model.Item;
import com.artzvrzn.store.catalogue.model.ItemQueryParams;
import com.artzvrzn.store.catalogue.service.api.IItemService;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

  @Autowired
  private IItemService itemService;

  @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<Item> get(
      @RequestParam("page") int page,
      @RequestParam("size") int size,
      ItemQueryParams params
  ) {
    return itemService.get(page, size, params);
  }

  @PostMapping(
      value = {"/", ""},
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public Item create(@RequestBody Item item) {
    return itemService.create(item);
  }

  @PutMapping(
      value = {"/{uuid}/{updated}", "/{uuid}/{updated}/"},
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public Item update(
      @RequestBody Item item,
      @PathVariable("uuid") UUID id,
      @PathVariable("updated") LocalDateTime updated
  ) {
    return itemService.update(item, id, updated);
  }
}
