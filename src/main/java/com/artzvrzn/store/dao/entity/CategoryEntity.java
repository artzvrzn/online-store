package com.artzvrzn.store.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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

}
