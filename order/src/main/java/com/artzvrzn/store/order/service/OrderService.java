package com.artzvrzn.store.order.service;

import com.artzvrzn.store.order.dao.OrderRepository;
import com.artzvrzn.store.order.domain.Order;
import com.artzvrzn.store.order.domain.OrderStatus;
import com.artzvrzn.store.order.dto.request.OrderRequest;
import com.artzvrzn.store.order.dto.response.OrderResponse;
import com.artzvrzn.store.order.exception.OrderNotFoundException;
import com.artzvrzn.store.order.service.api.IOrderService;
import com.artzvrzn.store.order.service.api.IUserService;
import com.artzvrzn.store.order.validation.ItemsValidator;
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
  private final ItemsValidator itemsValidator;

  @Override
  public void checkout(OrderRequest requestDto) {
    itemsValidator.validate(requestDto.getItems());
    Order order = mapper.map(requestDto, Order.class);
    order.setStatus(OrderStatus.CREATED);
    order.setUserInfo(userService.getAuthenticatedUser());
    orderRepository.save(order);
  }

  @Override
  public void update(UUID id, OrderRequest dto) {
    Order order = getOrThrowNotFound(id);
    order.setItems(dto.getItems());
    order.setPaymentType(dto.getPaymentType());
    orderRepository.save(order);
  }

  @Override
  public Page<OrderResponse> get(int page, int size) {
    PageRequest request = PageRequest.of(page, size);
    UUID userId = userService.getAuthenticatedUser().getUserId();
    return orderRepository
      .findAllByUserInfo_UserId(userId, request).map(e -> mapper.map(e, OrderResponse.class));
  }

  @Override
  public OrderResponse get(UUID id) {
    Order order = getOrThrowNotFound(id);
    return mapper.map(order, OrderResponse.class);
  }

  private Order getOrThrowNotFound(UUID id) {
    return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
  }
}
