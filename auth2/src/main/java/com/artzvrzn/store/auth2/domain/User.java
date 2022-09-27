package com.artzvrzn.store.auth2.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private UUID id;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private Set<Role> roles;

  public Map<String, Object> getAttributesMap() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("username", this.username);
    claims.put("firstName", this.getFirstName());
    claims.put("lastName", this.getLastName());
    claims.put("roles", this.getRoles());
    return claims;
  }
}
