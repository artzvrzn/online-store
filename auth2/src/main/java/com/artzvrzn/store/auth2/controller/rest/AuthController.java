package com.artzvrzn.store.auth2.controller.rest;

import com.artzvrzn.store.auth2.dto.request.AuthRequest;
import com.artzvrzn.store.auth2.dto.request.RefreshTokenRequest;
import com.artzvrzn.store.auth2.dto.request.UserRequest;
import com.artzvrzn.store.auth2.dto.response.JwtResponse;
import com.artzvrzn.store.auth2.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping(
    value = "/login",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public JwtResponse login(@RequestBody AuthRequest authRequest) {
    return authService.login(authRequest);
  }

  @PostMapping(
    value = "/sign_up",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public JwtResponse signUp(@RequestBody UserRequest userRequest) {
    return authService.signUp(userRequest);
  }

  @PostMapping(
    value = "/token",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public JwtResponse getNewAccessToken(@RequestBody RefreshTokenRequest request) {
    return authService.getAccessToken(request.getToken());
  }

  @PostMapping(
    value = "/refresh",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public JwtResponse getNewRefreshToken(@RequestBody RefreshTokenRequest request) {
    return authService.refresh(request.getToken());
  }
}
