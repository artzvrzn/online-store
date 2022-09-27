package com.artzvrzn.store.order.service;

import com.artzvrzn.store.order.dao.OrderRepository;
import com.artzvrzn.store.order.domain.Order;
import com.artzvrzn.store.order.domain.OrderStatus;
import com.artzvrzn.store.order.dto.response.OrderResponse;
import com.artzvrzn.store.order.service.api.IOrderService;
import com.artzvrzn.store.order.service.api.IUserService;
import com.artzvrzn.store.order.storage.cart.CartStorage;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

  private final OrderRepository orderRepository;
  private final IUserService userService;
  private final ModelMapper mapper;

  @Override
  public void checkout(CartStorage cart) {
    Order order = new Order();
    order.setItems(cart.getItems());
    order.setStatus(OrderStatus.CREATED);
    order.setUserInfo(userService.getAuthenticatedUser());
    orderRepository.save(order);
    cart.clear();
  }

  @Override
  public void update(UUID id, Order order) {

  }

  @Override
  public Page<OrderResponse> get(int page, int size) {
    PageRequest request = PageRequest.of(page, size);
    return orderRepository.findAll(request).map(e -> mapper.map(e, OrderResponse.class));
  }

  @Override
  public OrderResponse get(UUID id) {
    return null;
  }
}
