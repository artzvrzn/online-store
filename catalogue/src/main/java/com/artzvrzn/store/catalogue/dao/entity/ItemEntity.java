package com.artzvrzn.store.catalogue.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "Item")
@Table(name = "item", schema = "app")
public class ItemEntity {
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
  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  private Set<RatingEntity> rating;
  @Formula("(SELECT AVG(r.grade) FROM app.rating r WHERE r.item_id = id GROUP BY r.item_id)")
  private Integer avgRating;
}
