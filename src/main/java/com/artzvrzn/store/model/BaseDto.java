package com.artzvrzn.store.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseDto {

  private UUID id;
  private LocalDateTime created;
  private LocalDateTime updated;
}
