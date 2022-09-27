package com.artzvrzn.store.auth2.service.api;

import com.artzvrzn.store.auth2.domain.User;
import io.jsonwebtoken.Claims;

public interface JwtProvider {

  String generateAccessToken(User user);

  String generateRefreshToken(User user);

  boolean validateAccessToken(String accessToken);

  boolean validateRefreshToken(String refreshToken);

  Claims getRefreshTokenClaims(String refreshToken);

  Claims getAccessTokenClaims(String accessToken);
}
