package com.artzvrzn.store.order.domain;

import java.util.UUID;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
  private UUID userId;
  private String username;
  private String firstName;
  private String lastName;
  private String address;
}
