package com.artzvrzn.store.catalogue.dao.entity;

import com.artzvrzn.store.catalogue.model.Grade;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Rating")
@Table(name = "rating", schema = "app")
public class RatingEntity {
  @Id
  @SequenceGenerator(
      name = "ratingGenerator", sequenceName = "ratingSequence", schema = "app", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratingGenerator")
  @Column(unique = true)
  private Long id;
  private Grade grade;
  private UUID userId;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(
      name = "item_id",
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "item_rating_fk")
  )
  private ItemEntity item;

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + id.hashCode();
    hash = hash * 31 + (userId != null ? userId.hashCode() : 0);
    hash = hash * 31 + (item != null ? item.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (!(other instanceof RatingEntity)) return false;
    RatingEntity rating = (RatingEntity) other;
    return this.id.equals(rating.id)
        && this.grade == rating.grade
        && this.userId.equals(rating.userId)
        && this.item.equals(rating.item);
  }
}
