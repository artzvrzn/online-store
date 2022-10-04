package com.artzvrzn.store.catalogue.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemQueryParams {
  private final ItemOrder order;
  private final UUID category;
  private final List<String> brand;
  private final Integer minPrice;
  private final Integer maxPrice;

  @JsonCreator
  public ItemQueryParams(
      @JsonProperty String order,
      @JsonProperty UUID category,
      @JsonProperty List<String> brand,
      @JsonProperty Integer min,
      @JsonProperty Integer max
  ) {
    this.order = ItemOrder.valueOfOrDefault(order);
    this.category = category;
    this.brand = brand;
    this.minPrice = min;
    this.maxPrice = max;
  }
}
