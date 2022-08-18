package com.artzvrzn.store.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category extends BaseDto {

  private String name;
  private Category parentCategory;
  private List<Category> subcategories;
}
