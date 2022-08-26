package com.artzvrzn.store.catalogue.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(SnakeCaseStrategy.class)
public class Currency extends BaseDto {
  private String name;
  private String description;
  private BigDecimal rate;
}
