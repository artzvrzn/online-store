package com.artzvrzn.store.auth2.service.api;

import com.artzvrzn.store.auth2.dto.request.AuthRequest;
import com.artzvrzn.store.auth2.dto.request.UserRequest;
import com.artzvrzn.store.auth2.dto.response.JwtResponse;

public interface AuthService {

  JwtResponse login(AuthRequest authRequest);

  JwtResponse signUp(UserRequest userRequest);

  JwtResponse getAccessToken(String refreshToken);

  JwtResponse refresh(String refreshToken);
}
