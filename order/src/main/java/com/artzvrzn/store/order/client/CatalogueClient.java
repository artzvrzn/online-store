package com.artzvrzn.store.order.client;

import feign.Headers;
import feign.Param;
import java.util.Map;
import java.util.UUID;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("catalogue-service/catalogue")
public interface CatalogueClient {

  @GetMapping("/backend/item/{id}")
  Item get(@PathVariable("id") UUID id);

  @GetMapping(value = "/backend/validate_order", consumes = MediaType.APPLICATION_JSON_VALUE)
  Boolean validate(
    @RequestHeader("Authorization") String token, @SpringQueryMap Map<String, String> items);
}
