package com.artzvrzn.store.dao.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity {

  @Id
  private UUID id;
  private LocalDateTime created;
  private LocalDateTime updated;

}
