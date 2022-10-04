package com.artzvrzn.store.catalogue.controller.rest;

import com.artzvrzn.store.catalogue.dto.request.ItemRequest;
import com.artzvrzn.store.catalogue.dto.response.ItemResponse;
import com.artzvrzn.store.catalogue.domain.ItemQueryParams;
import com.artzvrzn.store.catalogue.service.api.ItemService;
import java.util.UUID;
import javax.validation.Valid;
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
  private ItemService itemService;

  @GetMapping(value = "/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ItemResponse get(@PathVariable("id") UUID id) {
    return itemService.get(id);
  }

  @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<ItemResponse> get(
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
  public ItemResponse create(@RequestBody @Valid ItemRequest request) {
    return itemService.create(request);
  }

  @PutMapping(
    value = {"/{id}", "/{id}/"},
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public ItemResponse update(
    @RequestBody @Valid ItemRequest request,
    @PathVariable("id") UUID id
    ) {
    return itemService.update(request, id);
  }
}
