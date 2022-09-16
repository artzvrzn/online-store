package com.artzvrzn.store.order.domain;

import com.artzvrzn.store.order.service.Cart;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order", schema = "app")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @Column(updatable = false, columnDefinition = "timestamp(3)")
  private LocalDateTime created;
  @Column(columnDefinition = "timestamp(3)")
  private LocalDateTime updated;
  @ElementCollection
  @CollectionTable(name = "order_item",
      joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
  @MapKeyColumn(name = "item_id")
  @Column(name = "quantity")
  private Map<UUID, Integer> items;
  @Embedded
  private UserInfo userInfo;
  @Embedded
  private Payment payment;
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  public void setItems(Cart cart) {
    this.items = cart.getItems();
  }

  public Cart getItems() {
    return new Cart(items);
  }
}
