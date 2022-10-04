package com.artzvrzn.store.catalogue.dto.request;

import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {
  @NotBlank
  private String name;
  @NotBlank
  private String description;
  @NotBlank
  private String brand;
  @DecimalMin(value = "0.0")
  private BigDecimal price;
  private String image;
  @NotNull
  private UUID category;
}
