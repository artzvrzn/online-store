package com.artzvrzn.store.catalogue.dao.api;

import com.artzvrzn.store.catalogue.dao.entity.RatingEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

  RatingEntity findByUserIdAndItem_Id(UUID userId, UUID itemId);

  List<RatingEntity> findAllByItem_Id(UUID itemId);

  List<RatingEntity> findAllByUserId(UUID userId);
}
