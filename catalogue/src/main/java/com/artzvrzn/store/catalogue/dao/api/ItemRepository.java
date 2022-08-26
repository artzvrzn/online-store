package com.artzvrzn.store.catalogue.dao.api;

import com.artzvrzn.store.catalogue.dao.entity.ItemEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository
    extends JpaRepository<ItemEntity, UUID>, JpaSpecificationExecutor<ItemEntity> {
}
