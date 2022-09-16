package com.artzvrzn.store.order.service;

import com.artzvrzn.store.order.service.api.ICart;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Cart implements ICart {
  private Map<UUID, Integer> items;

  public Cart() {
    this.items = new HashMap<>();
  }

  public Cart(Map<UUID, Integer> items) {
    this.items = items;
  }

  @Override
  public void add(UUID item) {
    if (items.computeIfPresent(item, (k, v) -> v + 1) == null) {
      items.put(item, 1);
    }
  }

  @Override
  public void add(UUID item, int quantity) {
    if (items.computeIfPresent(item, (k, v) -> v + quantity) == null) {
      items.put(item, quantity);
    }
  }

  @Override
  public void clear() {
    items.clear();
  }

  @Override
  public void decrement(UUID item) {

  }

  @Override
  public void remove(UUID item) {
    items.remove(item);
  }

  public Map<UUID, Integer> getItems() {
    return Collections.unmodifiableMap(items);
  }
}
