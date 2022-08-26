package com.artzvrzn.store.catalogue.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(SnakeCaseStrategy.class)
public class Item extends BaseDto {
  private UUID id;
  private String name;
  private String description;
  private String brand;
  private BigDecimal price;
  private UUID category;
  private String image;
  @JsonProperty("rating")
  private int avgRating;
}
