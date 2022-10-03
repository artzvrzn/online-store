package com.artzvrzn.store.order.validation;

import com.artzvrzn.store.order.client.CatalogueClient;
import com.artzvrzn.store.order.client.util.PasswordGrantTypeServiceToken;
import com.artzvrzn.store.order.client.util.ServiceToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemsValidator {

  @Autowired
  private CatalogueClient catalogueClient;
  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private ServiceToken serviceToken;

  public void validate(Map<UUID, Integer> items) {
    Map<String, String> queryMap = items.entrySet()
      .stream()
      .collect(Collectors.toMap(e -> e.getKey().toString(), String::valueOf));
//    JwtAuthenticationToken authentication =
//      (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    String token = "Bearer " + serviceToken.getToken();
    if (!catalogueClient.validate(token, queryMap)) {
      throw new IllegalArgumentException("Invalid items");
    }
  }
}
