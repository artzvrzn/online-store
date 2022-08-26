package com.artzvrzn.store.catalogue.model;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Rating {
  private Grade grade;
  private UUID item;
  private UUID user;
}
