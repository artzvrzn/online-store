package com.artzvrzn.store.catalogue.domain;

import java.util.UUID;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfo {
  private UUID userId;
  private String username;
}
