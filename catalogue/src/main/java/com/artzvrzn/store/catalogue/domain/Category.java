package com.artzvrzn.store.catalogue.domain;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category", schema = "app")
public class Category extends AuditableEntity<UUID, UUID> {
  @Id
  @GeneratedValue(generator = "UUID")
  @Setter(value = AccessLevel.PRIVATE)
  private UUID id;
  @Column(unique = true)
  private String name;
  @ManyToOne
  @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(name = "category_category_fk"))
  private Category parentCategory;
  @OneToMany(mappedBy = "parentCategory")
  private Set<Category> subcategories;

  Category() {}

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private final Category category;

    private Builder() {
      this.category = new Category();
    }

    public Builder withName(String name) {
      category.setName(name);
      return this;
    }

    public Builder withParentCategory(Category category) {
      if (category != null) {
        this.category.setParentCategory(category);
      }
      return this;
    }

    public Category build() {
      return category;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return Objects.equals(id, category.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

