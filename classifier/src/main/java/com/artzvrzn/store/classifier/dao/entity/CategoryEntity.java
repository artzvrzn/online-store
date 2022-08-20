package com.artzvrzn.store.classifier.dao.entity;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "category", schema = "app")
public class CategoryEntity extends BaseEntity<UUID> {
  @EqualsAndHashCode.Exclude
  @Column(unique = true)
  private String name;
  @EqualsAndHashCode.Exclude
  @ManyToOne
  @JoinColumn(referencedColumnName = "id")
  private CategoryEntity parentCategory;
  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "parentCategory")
  private Set<CategoryEntity> subcategories;
}

