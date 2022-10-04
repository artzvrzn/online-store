package com.artzvrzn.store.catalogue.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<T> {
  @CreatedBy
  protected T createdBy;
  @LastModifiedBy
  protected T modifiedBy;
  @CreatedDate
  @Column(columnDefinition = "timestamp(3)")
  protected LocalDateTime created;
  @LastModifiedDate
  @Version
  @Column(columnDefinition = "timestamp(3)")
  protected LocalDateTime updated;
}
