package com.artzvrzn.store.auth.controller.rest;

import com.artzvrzn.store.auth.domain.User;
import com.artzvrzn.store.auth.dto.LoginDto;
import com.artzvrzn.store.auth.dto.UserDto;
import com.artzvrzn.store.auth.service.Oauth2UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login_custom")
public class LoginController {

  @Autowired
  private Oauth2UserDetailsService userDetailsService;


  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public String login() {
    return "send name and password in body";
  }
}
