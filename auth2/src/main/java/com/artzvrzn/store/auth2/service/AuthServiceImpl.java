package com.artzvrzn.store.auth2.service;

import com.artzvrzn.store.auth2.dao.nosql.RefreshTokenRepository;
import com.artzvrzn.store.auth2.domain.RefreshToken;
import com.artzvrzn.store.auth2.dto.request.UserRequest;
import com.artzvrzn.store.auth2.exception.AuthenticationFailedException;
import com.artzvrzn.store.auth2.service.api.AuthService;
import com.artzvrzn.store.auth2.service.api.RefreshTokenService;
import com.artzvrzn.store.auth2.service.api.UserService;
import com.artzvrzn.store.auth2.domain.User;
import com.artzvrzn.store.auth2.dto.request.AuthRequest;
import com.artzvrzn.store.auth2.dto.response.JwtResponse;
import com.artzvrzn.store.auth2.service.api.JwtProvider;
import io.jsonwebtoken.Claims;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;
  private final RefreshTokenService refreshTokenService;

  @Override
  public JwtResponse login(AuthRequest authRequest) {
    User user = userService.getByUsername(authRequest.getUsername());
    if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
      throw new AuthenticationFailedException();
    }
    String accessToken = jwtProvider.generateAccessToken(user);
    String refreshToken = jwtProvider.generateRefreshToken(user);
    refreshTokenService.save(user.getId().toString(), refreshToken);
    return new JwtResponse(accessToken, refreshToken);
  }

  @Override
  public JwtResponse signUp(UserRequest userRequest) {
    User user = userService.create(userRequest);
    String accessToken = jwtProvider.generateAccessToken(user);
    String refreshToken = jwtProvider.generateRefreshToken(user);
    refreshTokenService.save(user.getId().toString(), refreshToken);
    return new JwtResponse(accessToken, refreshToken);
  }

  @Override
  public JwtResponse getAccessToken(String refreshToken) {
    if (jwtProvider.validateRefreshToken(refreshToken)) {
      Claims claims = jwtProvider.getRefreshTokenClaims(refreshToken);
      String userId = claims.getSubject();
      if (refreshTokenService.isValid(userId, refreshToken)) {
        User user = userService.getById(UUID.fromString(userId));
        return new JwtResponse(jwtProvider.generateAccessToken(user), null);
      }
    }
    return new JwtResponse(null, null);
  }

  @Override
  public JwtResponse refresh(String refreshToken) {
    if (jwtProvider.validateRefreshToken(refreshToken)) {
      Claims claims = jwtProvider.getRefreshTokenClaims(refreshToken);
      String userId = claims.getSubject();
      if (refreshTokenService.isValid(userId, refreshToken)) {
        User user = userService.getById(UUID.fromString(userId));
        String newAccessToken = jwtProvider.generateAccessToken(user);
        String newRefreshToken = jwtProvider.generateRefreshToken(user);
        refreshTokenService.update(userId, newRefreshToken);
        return new JwtResponse(newAccessToken, refreshToken);
      }
    }
    throw new AuthenticationFailedException("Invalid token");
  }
}
