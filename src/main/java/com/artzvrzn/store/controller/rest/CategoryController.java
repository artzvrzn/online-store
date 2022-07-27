package com.artzvrzn.store.controller.rest;

import com.artzvrzn.store.model.Category;
import com.artzvrzn.store.view.api.ICategoryService;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private ICategoryService categoryService;

  @GetMapping(value = {"/{uuid}", "/{uuid}/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Category get(@PathVariable("uuid") UUID id) {
    return categoryService.get(id);
  }

  @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<Category> get(@RequestParam("page") int page, @RequestParam("size") int size) {
    return categoryService.get(page, size);
  }

  @PostMapping(
      value = {"/", ""},
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public Category create(@RequestBody Category category) {
    return categoryService.create(category);
  }

  @PutMapping(
      value = {"/{uuid}/{updated}", "/{uuid}/{updated}/"},
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public Category update(
      @PathVariable("uuid") UUID id,
      @PathVariable("updated") LocalDateTime updated,
      @RequestBody Category category
  ) {
    return categoryService.update(id, updated, category);
  }

  @DeleteMapping(
      value = {"{uuid}/{updated}", "{uuid}/{updated}"},
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public void delete(
      @PathVariable("uuid") UUID id,
      @PathVariable("updated") LocalDateTime updated
  ) {
    categoryService.delete(id, updated);
  }
}
