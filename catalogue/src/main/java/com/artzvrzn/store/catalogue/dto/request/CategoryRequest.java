package com.artzvrzn.store.catalogue.dto.request;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
  @NotBlank
  private String name;
  private UUID parentCategory;
}
