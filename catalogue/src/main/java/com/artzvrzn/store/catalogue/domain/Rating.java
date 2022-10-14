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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rating", schema = "app")
public class Rating extends AuditableEntity<UUID, Long> {

  @Id
  @SequenceGenerator(
    name = "ratingGenerator", sequenceName = "ratingSequence", schema = "app", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratingGenerator")
  private Long id;
  private int grade;
  private UUID userId;
  @ManyToOne
  @JoinColumn(
    name = "item_id",
    referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "item_rating_fk")
  )
  private Item item;

  Rating() {}

  public static Builder getBuilder() {
    return new Builder();
  }

  public static class Builder {
    private final Rating rating;

    private Builder() {
      this.rating = new Rating();
    }

    public Builder grade(int grade) {
      rating.setGrade(grade);
      return this;
    }

    public Builder userId(UUID userId) {
      rating.setUserId(userId);
      return this;
    }

    public Builder item(Item item) {
      rating.setItem(item);
      return this;
    }

    public Rating build() {
      return rating;
    }
  }
}
