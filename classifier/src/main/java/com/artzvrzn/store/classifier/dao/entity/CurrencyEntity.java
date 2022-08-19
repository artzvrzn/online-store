package com.artzvrzn.store.classifier.dao.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "currency", schema = "app")
public class CurrencyEntity extends BaseEntity<String> {
  @Column(nullable = false)
  private String description;
  @Column(nullable = false)
  private BigDecimal rate;
}
