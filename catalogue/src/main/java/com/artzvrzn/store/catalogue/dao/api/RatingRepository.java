package com.artzvrzn.store.catalogue.dao.api;

import com.artzvrzn.store.catalogue.domain.Rating;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

  Rating findByUserIdAndItem_Id(UUID userId, UUID itemId);

  List<Rating> findAllByItem_Id(UUID itemId);

  List<Rating> findAllByUserId(UUID userId);
}
