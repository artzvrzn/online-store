package com.artzvrzn.store.order.service;

import com.artzvrzn.store.order.domain.UserInfo;
import com.artzvrzn.store.order.service.api.IUserService;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Override
  public UserInfo getAuthenticatedUser() {
    Jwt token = ((JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication())
        .getToken();
    return UserInfo.builder()
        .userId(UUID.fromString(token.getClaim("sub")))
        .username(token.getClaim("preferred_username"))
        .firstName(token.getClaim("given_name"))
        .lastName(token.getClaim("family_name"))
        .build();
  }
}
