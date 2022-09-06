package com.artzvrzn.store.auth.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role", schema = "app")
public class Role extends BaseEntity {
  @Id
  @Column(updatable = false)
  private String name;
  @ManyToMany(mappedBy = "roles")
  private List<User> users = new ArrayList<>();

  Role() {};

  public Role(String name) {
    this.name = name;
  }
}
