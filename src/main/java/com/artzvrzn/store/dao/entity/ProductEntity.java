package com.artzvrzn.store.dao.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "product", schema = "app")
public class ProductEntity extends BaseEntity {

  private String name;
  private String description;
  private BigDecimal price;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private CategoryEntity category;
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private ImageEntity cover;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "product_images",
      schema = "app",
      joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id")
  )
  private Set<ImageEntity> images = new HashSet<>();

}
