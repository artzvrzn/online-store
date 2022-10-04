package com.artzvrzn.store.catalogue.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stock", schema = "app")
public class Stock extends AuditableEntity<UUID> {
  @Id
  @Column(name = "item_id")
  private UUID id;
  private int quantity = 0;
  @Enumerated(EnumType.STRING)
  private Measure unit = Measure.PIECE;
  @OneToOne
  @MapsId
  @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "item_stock_fk"))
  private Item item;

  Stock() {}

  public Stock(Item item) {
    this.item = item;
  }
}
