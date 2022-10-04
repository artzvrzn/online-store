package com.artzvrzn.store.catalogue.dao.api;

import com.artzvrzn.store.catalogue.domain.Stock;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {

}
