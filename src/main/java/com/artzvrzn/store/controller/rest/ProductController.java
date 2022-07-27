package com.artzvrzn.store.controller.rest;

import com.artzvrzn.store.model.Product;
import com.artzvrzn.store.view.api.IProductService;
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
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private IProductService productService;

  @GetMapping(value = {"/{uuid}", "/{uuid}/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Product get(@PathVariable("uuid") UUID id) {
    return productService.get(id);
  }

  @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<Product> get(@RequestParam("page") int page, @RequestParam("size") int size) {
    return productService.get(page, size);
  }

  @PostMapping(
      value = {"/", ""},
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public Product create(@RequestBody Product product) {
    return productService.create(product);
  }

  @PutMapping(
      value = {"/{uuid}/{updated}", "/{uuid}/{updated}/"},
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public Product update(
      @PathVariable("uuid") UUID id,
      @PathVariable("updated") LocalDateTime updated,
      @RequestBody Product product
  ) {
    return productService.update(id, updated, product);
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
    productService.delete(id, updated);
  }
}
