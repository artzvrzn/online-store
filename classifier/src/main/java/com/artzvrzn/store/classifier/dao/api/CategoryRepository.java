package com.artzvrzn.store.classifier.dao.api;

import com.artzvrzn.store.classifier.dao.entity.CategoryEntity;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

  Page<CategoryEntity> findAllByParentCategory_Id(UUID parentId, Pageable pageable);
}