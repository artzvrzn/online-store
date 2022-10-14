package com.artzvrzn.store.catalogue.domain;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item", schema = "app")
public class Item extends AuditableEntity<UUID, UUID> {
  @Id
  @GeneratedValue(generator = "UUID")
  @Setter(value = AccessLevel.PRIVATE)
  private UUID id;
  private String name;
  private String description;
  private String brand;
  private BigDecimal price;
  private String image;
  @ManyToOne
  @JoinColumn(
    name = "category_id",
    referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "item_category_fk")
  )
  private Category category;

  Item() {}

  public static Builder getBuilder() {
    return new Builder();
  }

  public static class Builder {

    private final Item item;

    private Builder() {
      this.item = new Item();
    }

    public Builder name(String name) {
      item.setName(name);
      return this;
    }

    public Builder description(String description) {
      item.setDescription(description);
      return this;
    }

    public Builder brand(String brand) {
      item.setBrand(brand);
      return this;
    }

    public Builder price(BigDecimal price) {
      item.setPrice(price);
      return this;
    }

    public Builder image(String image) {
      item.setImage(image);
      return this;
    }

    public Builder category(Category category) {
      item.setCategory(category);
      return this;
    }

    public Item build() {
      return this.item;
    }
  }
}
