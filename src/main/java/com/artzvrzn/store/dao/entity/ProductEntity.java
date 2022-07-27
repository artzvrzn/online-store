package com.artzvrzn.store.dao.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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

  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String description;
  @Column(nullable = false)
  private BigDecimal price;
  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private CategoryEntity category;
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private ImageEntity cover;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "product_images",
      schema = "app",
      joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id")
  )
  private List<ImageEntity> images = new ArrayList<>();
}
