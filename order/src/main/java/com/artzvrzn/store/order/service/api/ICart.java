package com.artzvrzn.store.order.service.api;

import java.util.Map;
import java.util.UUID;

public interface ICart {

  void add(UUID item);

  void add(UUID item, int quantity);

  void clear();

  void decrement(UUID item);

  void remove(UUID item);

  Map<UUID, Integer> getItems();
}
