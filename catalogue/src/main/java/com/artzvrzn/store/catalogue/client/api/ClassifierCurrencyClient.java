package com.artzvrzn.store.catalogue.client.api;

import com.artzvrzn.store.catalogue.model.Category;
import com.artzvrzn.store.catalogue.model.Currency;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("classifier-service/classifier/currency")
public interface ClassifierCurrencyClient {

  @GetMapping(value = "/{name}")
  Currency get(@PathVariable String name);

  @GetMapping(value = "/")
  List<Currency> getAllCurrencies();

  @RequestMapping(value = "/{name}", method = RequestMethod.HEAD)
  ResponseEntity<Void> headForHeaders(@PathVariable("name") String name);
}
