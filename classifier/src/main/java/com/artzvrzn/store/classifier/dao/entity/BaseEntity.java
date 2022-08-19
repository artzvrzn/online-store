package com.artzvrzn.store.classifier.dao.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity<ID> {
  @Id
  private ID id;
  @Column(columnDefinition = "timestamp(3)")
  private LocalDateTime created;
  @Column(columnDefinition = "timestamp(3)")
  private LocalDateTime updated;
}
