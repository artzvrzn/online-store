package com.artzvrzn.store.catalogue.dto;

import com.artzvrzn.store.catalogue.domain.Measure;
import com.artzvrzn.store.catalogue.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ItemDto implements Dto {
  @JsonProperty(access = Access.READ_ONLY)
  private UUID id;
  @JsonProperty(access = Access.READ_ONLY)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime created;
  @JsonProperty(access = Access.READ_ONLY)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime updated;
  @NotBlank
  private String name;
  @NotBlank
  private String description;
  @NotBlank
  private String brand;
  @DecimalMin(value = "0.0")
  private BigDecimal price;
  private String image;
  @JsonProperty(access = Access.READ_ONLY)
  private int quantity;
  @JsonProperty(access = Access.READ_ONLY)
  private Measure measure;
  @NotNull
  private UUID category;
}
