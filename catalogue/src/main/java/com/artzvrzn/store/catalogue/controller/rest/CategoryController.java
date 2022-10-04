package com.artzvrzn.store.catalogue.controller.rest;

import com.artzvrzn.store.catalogue.dto.request.CategoryRequest;
import com.artzvrzn.store.catalogue.dto.response.CategoryResponse;
import com.artzvrzn.store.catalogue.service.api.CategoryService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping(value = {"/{uuid}", "/{uuid}/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public CategoryResponse get(@PathVariable("uuid") UUID id) {
    return categoryService.get(id);
  }

  @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryResponse> getTopLevelCategories() {
    return categoryService.getTopLevelCategories();
  }

  @GetMapping(value = {"/all", "/all/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryResponse> getAllCategories() {
    return categoryService.getAll();
  }

  @GetMapping(
    value = {"/sub/{uuid}", "/sub/{uuid}/"},
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryResponse> getDirectSubcategories(@PathVariable("uuid") UUID parentId) {
    return categoryService.getDirectSubcategories(parentId);
  }

  @GetMapping(
    value = {"/sub_all/{uuid}", "/sub_all/{uuid}/"},
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryResponse> getIndirectSubcategories(@PathVariable("uuid") UUID parentId) {
    return categoryService.getIndirectSubcategories(parentId);
  }

  @RequestMapping(value = {"/{uuid}", "/{uuid}/"}, method = RequestMethod.HEAD)
  public ResponseEntity<?> isExist(@PathVariable("uuid") UUID id) {
    return categoryService.isExist(id) ?
      ResponseEntity.ok().build() : ResponseEntity.noContent().build();
  }

  @PostMapping(
    value = {"/", ""},
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryResponse create(@RequestBody CategoryRequest request) {
    return categoryService.create(request);
  }

  @PutMapping(
    value = {"/{uuid}", "/{uuid}/"},
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public CategoryResponse update(
    @PathVariable("uuid") UUID id,
    @RequestBody CategoryRequest request
  ) {
    return categoryService.update(request, id);
  }

  @DeleteMapping(value = {"{uuid}", "{uuid}/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("uuid") UUID id) {
    categoryService.delete(id);
  }
}
