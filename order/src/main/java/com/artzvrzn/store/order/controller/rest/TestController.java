package com.artzvrzn.store.order.controller.rest;

import com.artzvrzn.store.order.client.util.PasswordGrantTypeServiceToken;
import com.artzvrzn.store.order.client.util.ServiceToken;
import com.artzvrzn.store.order.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private IUserService userService;
  @Autowired
  private ServiceToken serviceToken;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public String userInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return serviceToken.getToken();
  }
}
