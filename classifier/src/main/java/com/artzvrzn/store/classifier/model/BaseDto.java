package com.artzvrzn.store.classifier.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDto {
  @JsonProperty(access = Access.READ_ONLY)
  private LocalDateTime created;
  @JsonProperty(access = Access.READ_ONLY)
  private LocalDateTime updated;
}
