package com.artzvrzn.store.classifier.dao.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category", schema = "app")
public class CategoryEntity extends BaseEntity {

  private String name;
  @ManyToOne
  @JoinColumn(referencedColumnName = "id")
  private CategoryEntity parentCategory;
}

