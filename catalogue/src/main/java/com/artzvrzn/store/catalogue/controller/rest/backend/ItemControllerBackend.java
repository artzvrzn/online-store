package com.artzvrzn.store.catalogue.controller.rest.backend;

import com.artzvrzn.store.catalogue.model.Item;
import com.artzvrzn.store.catalogue.service.api.IItemService;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend")
public class ItemControllerBackend {

  @Autowired
  private IItemService itemService;

  @GetMapping(value = "/item/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Item get(@PathVariable("uuid") UUID id) {
    return itemService.get(id);
  }

  @GetMapping(value = "/validate_order")
  @ResponseStatus(HttpStatus.OK)
  public boolean get(@RequestParam Map<UUID, Integer> order) {
    Authentication token =  SecurityContextHolder.getContext().getAuthentication();
    return order != null;
  }
}
