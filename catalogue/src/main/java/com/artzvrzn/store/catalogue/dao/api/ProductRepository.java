package com.artzvrzn.store.catalogue.dao.api;

import com.artzvrzn.store.catalogue.dao.entity.ProductEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
    extends JpaRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity> {

  @Modifying
  @Query("UPDATE Product p SET p.rating = p.rating + 1 WHERE p.id = :id")
  void incrementRating(@Param("id") UUID id);

  @Modifying
  @Query("UPDATE Product p SET p.rating = p.rating - 1 WHERE p.id = :id")
  void decrementRating(@Param("id") UUID id);
}
