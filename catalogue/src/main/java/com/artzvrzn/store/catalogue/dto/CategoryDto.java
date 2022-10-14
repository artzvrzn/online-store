package com.artzvrzn.store.catalogue.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto implements Dto {
  private UUID id;
  private String name;
  private UUID parentCategory;
}
