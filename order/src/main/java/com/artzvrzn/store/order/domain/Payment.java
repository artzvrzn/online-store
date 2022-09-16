package com.artzvrzn.store.order.domain;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Payment {
  private PaymentType type;
  private boolean payed;
}
