package com.artzvrzn.store.classifier.dao.api;

import com.artzvrzn.store.classifier.dao.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, String> {

}
