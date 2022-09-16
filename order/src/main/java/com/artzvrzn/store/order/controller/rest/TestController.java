package com.artzvrzn.store.order.controller.rest;

import com.artzvrzn.store.order.domain.UserInfo;
import com.artzvrzn.store.order.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private IUserService userService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public UserInfo userInfo() {
    return userService.getAuthenticatedUser();
  }
}
