package com.artzvrzn.store.catalogue.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseDto {
  private UUID id;
  private String name;
  private String description;
  private String brand;
  private BigDecimal price;
  private UUID category;
  private String image;
  private int rating;
}
