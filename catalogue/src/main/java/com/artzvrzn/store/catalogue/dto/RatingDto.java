package com.artzvrzn.store.catalogue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.UUID;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(SnakeCaseStrategy.class)
public class RatingDto {
  @Min(1)
  @Max(5)
  private int grade;
  @JsonProperty(access = Access.READ_ONLY)
  private UUID userId;
  @JsonProperty(access = Access.READ_ONLY)
  private UUID itemId;
}
