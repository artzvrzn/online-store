package com.artzvrzn.store.order.service.api;

import com.artzvrzn.store.order.service.Cart;
import com.artzvrzn.store.order.domain.Order;

public interface IOrderService {

  void create(Cart cart);

  void update(Order order);

  void get(Order order);
}
