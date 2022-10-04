package com.artzvrzn.store.catalogue.dto.response;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
  private UUID id;
  private String name;
  private UUID parentCategory;
}
