package com.artzvrzn.store.catalogue.controller.rest.backend;

import com.artzvrzn.store.catalogue.service.api.ItemService;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend")
public class BackendItemController {

  @Autowired
  private ItemService itemService;

  @GetMapping(value = "/validate_order")
  @ResponseStatus(HttpStatus.OK)
  public boolean get(@RequestParam Map<UUID, Integer> order) {
    Authentication token =  SecurityContextHolder.getContext().getAuthentication();
    return order != null;
  }
}
