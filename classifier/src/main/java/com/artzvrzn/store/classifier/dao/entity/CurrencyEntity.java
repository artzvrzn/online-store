package com.artzvrzn.store.classifier.dao.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "currency", schema = "app")
public class CurrencyEntity extends BaseEntity<String> {
  private String description;
  private BigDecimal rate;
}
