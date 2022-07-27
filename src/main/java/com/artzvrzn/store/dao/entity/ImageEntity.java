package com.artzvrzn.store.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "image", schema = "app")
public class ImageEntity extends BaseEntity {

  private String url;

}
