package com.artzvrzn.store.catalogue.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(SnakeCaseStrategy.class)
public class Category extends BaseDto {
  private UUID id;
  private String name;
  private UUID parentCategory;
}
