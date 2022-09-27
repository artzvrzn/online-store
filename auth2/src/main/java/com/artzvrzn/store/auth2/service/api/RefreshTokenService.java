package com.artzvrzn.store.auth2.service.api;

import com.artzvrzn.store.auth2.domain.RefreshToken;

public interface RefreshTokenService {

  RefreshToken get(String id);

  void save(String id, String token);

  void update(String id, String token);

  boolean isValid(String id, String token);
}
