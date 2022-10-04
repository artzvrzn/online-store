package com.artzvrzn.store.catalogue.domain;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item", schema = "app")
public class Item extends AuditableEntity<UUID> {
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
  @PrimaryKeyJoinColumn
  @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
  private Stock stock;
//  @Formula("(SELECT AVG(r.grade) FROM app.rating r WHERE r.item_id = id GROUP BY r.item_id)")
//  private Integer avgRating;

  Item() {}

  public static Builder getBuilder() {
    return new Builder();
  }

  public static class Builder {
    private final Item item;

    private Builder() {
      this.item = new Item();
    }

    public Builder withName(String name) {
      item.setName(name);
      return this;
    }

    public Builder withDescription(String description) {
      item.setDescription(description);
      return this;
    }

    public Builder withBrand(String brand) {
      item.setBrand(brand);
      return this;
    }

    public Builder withPrice(BigDecimal price) {
      item.setPrice(price);
      return this;
    }

    public Builder withImage(String image) {
      item.setImage(image);
      return this;
    }

    public Builder withCategory(Category category) {
      item.setCategory(category);
      return this;
    }

    public Builder withStock(Stock stock) {
      item.setStock(stock);
      return this;
    }

    public Item build() {
      return this.item;
    }
  }
}
