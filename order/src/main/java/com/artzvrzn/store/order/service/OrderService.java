package com.artzvrzn.store.order.service;

import com.artzvrzn.store.order.dao.OrderRepository;
import com.artzvrzn.store.order.domain.Order;
import com.artzvrzn.store.order.domain.OrderStatus;
import com.artzvrzn.store.order.service.api.IOrderService;
import com.artzvrzn.store.order.service.api.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

  private final OrderRepository orderRepository;
  private final IUserService userService;

  @Override
  public void create(Cart cart) {
    Order order = new Order();
    order.setItems(cart);
    order.setStatus(OrderStatus.CREATED);
    order.setUserInfo(userService.getAuthenticatedUser());
  }

  @Override
  public void update(Order order) {

  }

  @Override
  public void get(Order order) {

  }
}
