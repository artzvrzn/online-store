package com.artzvrzn.store.catalogue.dao.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Rating")
@Table(name = "rating", schema = "app")
public class RatingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(unique = true)
  private Long id;
  private UUID userId;
  @OneToMany
  private ProductEntity product;

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + id.hashCode();
    hash = hash * 31 + (userId != null ? userId.hashCode() : 0);
    hash = hash * 31 + (product != null ? product.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (!(other instanceof RatingEntity)) return false;
    RatingEntity rating = (RatingEntity) other;
    return this.id.equals(rating.id)
        && this.userId.equals(rating.userId)
        && this.product.equals(rating.product);
  }
}
