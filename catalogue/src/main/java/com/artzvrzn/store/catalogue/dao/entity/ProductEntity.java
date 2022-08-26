package com.artzvrzn.store.catalogue.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "Product")
@Table(name = "product", schema = "app")
public class ProductEntity {
  @Id
  private UUID id;
  @CreatedDate
  @Column(updatable = false, columnDefinition = "timestamp(3)")
  private LocalDateTime created;
  @Column(columnDefinition = "timestamp(3)")
  private LocalDateTime updated;
  private String name;
  private String description;
  private String brand;
  private BigDecimal price;
  private UUID category;
  private String image;
  @EqualsAndHashCode.Exclude
  private Set<RatingEntity> rating;
}
