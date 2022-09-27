package com.artzvrzn.store.auth2.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest {
  private String token;
}
