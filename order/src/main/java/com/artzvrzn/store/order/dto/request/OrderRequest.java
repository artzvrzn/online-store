package com.artzvrzn.store.order.dto.request;

import com.artzvrzn.store.order.domain.PaymentType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Map;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(SnakeCaseStrategy.class)
public class OrderRequest {
  @NotEmpty
  private Map<UUID, Integer> items;
  private PaymentType paymentType;
}
