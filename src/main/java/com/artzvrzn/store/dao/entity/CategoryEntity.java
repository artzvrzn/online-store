package com.artzvrzn.store.dao.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "category", schema = "app")
public class CategoryEntity extends BaseEntity {

  @Column(nullable = false)
  private String name;
  @ManyToOne
  private CategoryEntity parentCategory;
  @OneToMany(mappedBy = "parentCategory")
  private List<CategoryEntity> subcategories;
}
