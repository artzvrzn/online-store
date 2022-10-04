package com.artzvrzn.store.catalogue.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.domain.Sort;

public enum ItemOrder {

  PRICE_ASC("price:asc", Sort.by("price").ascending()),
  PRICE_DESC("price:desc", Sort.by("price").descending()),
  DATE_ASC("date:asc", Sort.by("created").ascending()),
  DATE_DESC("date:desc", Sort.by("created").descending()),
  DEFAULT("default", Sort.by("rating").descending());

  private final String query;
  private final Sort sort;

  ItemOrder(String query, Sort sort) {
    this.query = query;
    this.sort = sort;
  }

  @JsonCreator
  public static ItemOrder valueOfOrDefault(String query) {
    for (ItemOrder order : ItemOrder.values()) {
      if (order.getQuery().equalsIgnoreCase(query)) {
        return order;
      }
    }
    return DEFAULT;
  }

  public String getQuery() {
    return query;
  }

  public Sort getSort() {
    return sort;
  }
}

