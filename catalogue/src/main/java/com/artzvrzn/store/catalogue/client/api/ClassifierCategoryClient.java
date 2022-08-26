package com.artzvrzn.store.catalogue.client.api;

import com.artzvrzn.store.catalogue.model.Category;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("classifier-service/classifier/category")
public interface ClassifierCategoryClient {

  @GetMapping(value = "/{id}")
  Category get(@PathVariable UUID id);

  @GetMapping(value = "/")
  List<Category> getTopLevelCategories();

  @GetMapping(value = "/all")
  List<Category> getAllCategories();

  @GetMapping(value = "/{id}/direct_subcategory")
  List<Category> getDirectSubcategories(@PathVariable("id") UUID parentId);

  @GetMapping("/{id}/indirect_subcategory")
  List<Category> getIndirectSubcategories(@PathVariable("id") UUID parentId);

  @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
  ResponseEntity<Void> headForHeaders(@PathVariable("id") UUID id);
}
