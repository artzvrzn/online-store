package com.artzvrzn.store.catalogue.domain;

import java.util.UUID;
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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rating", schema = "app")
public class Rating {

  @Id
  @SequenceGenerator(
    name = "ratingGenerator", sequenceName = "ratingSequence", schema = "app", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratingGenerator")
  @Column(unique = true)
  private Long id;
  private Grade grade;
  private UUID userId;
  @ManyToOne
  @JoinColumn(
    name = "item_id",
    referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "item_rating_fk")
  )
  private Item item;
}
