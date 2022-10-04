package com.artzvrzn.store.catalogue.service;

import com.artzvrzn.store.catalogue.domain.UserInfo;
import com.artzvrzn.store.catalogue.service.api.UserService;
import java.util.UUID;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class JwtUserService implements UserService {

  @Override
  public UserInfo getAuthenticatedUser() {
    Jwt token = ((JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication())
        .getToken();
    UUID userId = UUID.fromString(token.getClaim("sub"));
    String username = token.getClaim("preferred_username");
    return new UserInfo(userId, username);
  }
}
