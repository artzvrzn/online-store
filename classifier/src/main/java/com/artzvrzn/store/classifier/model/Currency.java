package com.artzvrzn.store.classifier.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder(value = {"name created, updated, parent_category"})
public class Currency extends BaseDto {
  @NotBlank
  private String name;
  @NotBlank
  private String description;
  @NotNull
  @DecimalMin(value = "0.0", inclusive = false)
  private BigDecimal rate;
}
