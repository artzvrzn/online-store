package com.artzvrzn.store.catalogue.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDto {
  private LocalDateTime created;
  private LocalDateTime updated;
}
