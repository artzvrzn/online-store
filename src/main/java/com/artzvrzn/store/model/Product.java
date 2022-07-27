package com.artzvrzn.store.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseDto {

  private String name;
  private String description;
  private BigDecimal price;
  private Category category;
  @JsonProperty("cover")
  private Image cover;
  @JsonProperty("images")
  private Set<Image> images;

}
