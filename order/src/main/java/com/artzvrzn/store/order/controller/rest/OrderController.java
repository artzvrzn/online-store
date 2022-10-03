package com.artzvrzn.store.order.controller.rest;

import com.artzvrzn.store.order.dto.request.OrderRequest;
import com.artzvrzn.store.order.dto.response.OrderResponse;
import com.artzvrzn.store.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping(value = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<OrderResponse> getOrders(
    @RequestParam("page") int page,
    @RequestParam("size") int size) {
    return orderService.get(page, size);
  }

  @PostMapping(value = {"/checkout", "/checkout/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void completeOrder(@RequestBody OrderRequest request) {
    orderService.checkout(request);
  }
}
