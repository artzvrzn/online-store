package com.artzvrzn.store.order.service.api;

import com.artzvrzn.store.order.domain.Order;
import com.artzvrzn.store.order.dto.request.OrderRequest;
import com.artzvrzn.store.order.dto.response.OrderResponse;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface IOrderService {

  void checkout(OrderRequest orderRequest);

  void update(UUID id, OrderRequest orderRequest);

  Page<OrderResponse> get(int page, int size);

  OrderResponse get(UUID id);
}
