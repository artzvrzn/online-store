package com.artzvrzn.store.classifier.dao.api;

import com.artzvrzn.store.classifier.dao.entity.CategoryEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

  List<CategoryEntity> findAllByParentCategory_Id(UUID parentId, Sort sort);

  boolean existsByName(String name);
}
