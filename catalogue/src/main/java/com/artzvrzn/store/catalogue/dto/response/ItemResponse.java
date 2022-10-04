package com.artzvrzn.store.catalogue.dto.response;

import com.artzvrzn.store.catalogue.domain.Measure;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponse {
  private UUID id;
  private String name;
  private String description;
  private String brand;
  private BigDecimal price;
  private String image;
  private int quantity;
  private Measure measure;
  private UUID category;
}
