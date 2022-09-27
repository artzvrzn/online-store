package com.artzvrzn.store.order.service.api;

import com.artzvrzn.store.order.domain.Order;
import com.artzvrzn.store.order.dto.response.OrderResponse;
import com.artzvrzn.store.order.storage.cart.CartStorage;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface IOrderService {

  void checkout(CartStorage cart);

  void update(UUID id, Order order);

  Page<OrderResponse> get(int page, int size);

  OrderResponse get(UUID id);
}
