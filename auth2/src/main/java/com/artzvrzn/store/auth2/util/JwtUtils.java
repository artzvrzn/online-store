package com.artzvrzn.store.auth2.util;

import com.artzvrzn.store.auth2.domain.JwtAuthentication;
import com.artzvrzn.store.auth2.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

  public static JwtAuthentication generate(Claims claims) {
    JwtAuthentication authentication = new JwtAuthentication();
    authentication.setRoles(getRoles(claims));
    authentication.setFirstName(claims.get("firstName", String.class));
    authentication.setUsername(claims.getSubject());
    return authentication;
  }

  private static Set<Role> getRoles(Claims claims) {
    List<String> roles = claims.get("roles", List.class);
    return roles.stream()
        .map(Role::valueOf)
        .collect(Collectors.toSet());
  }
}
