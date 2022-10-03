package com.artzvrzn.store.order.domain;

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
import javax.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "order", schema = "app")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @CreationTimestamp
  @Column(updatable = false, columnDefinition = "timestamp(3)")
  private LocalDateTime created;
  @UpdateTimestamp
  @Version
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
  @Enumerated(EnumType.STRING)
  private PaymentType paymentType;
  @Enumerated(EnumType.STRING)
  private OrderStatus status;
}
