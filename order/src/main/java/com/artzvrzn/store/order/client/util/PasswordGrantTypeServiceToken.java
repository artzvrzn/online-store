package com.artzvrzn.store.order.client.util;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class PasswordGrantTypeServiceToken implements ServiceToken {
  private final RestTemplate restTemplate;
  @Value("${keycloak.service-authentication.username}")
  private String serviceUsername;
  @Value("${keycloak.service-authentication.password}")
  private String servicePassword;
  @Value("${keycloak.service-authentication.client-id}")
  private String clientId;
  @Value("${keycloak.service-authentication.client-secret}")
  private String clientSecret;
  @Value("${keycloak.service-authentication.token-url}")
  private String tokenUrl;

  public PasswordGrantTypeServiceToken(RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
  }

  private HttpEntity<MultiValueMap<String, String>> getHttpEntity() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("client_id", clientId);
    map.add("client_secret", clientSecret);
    map.add("username", serviceUsername);
    map.add("password", servicePassword);
    map.add("grant_type", "password");
    return new HttpEntity<>(map, httpHeaders);
  }

  @Override
  public String getToken() {
    log.info("Requesting for service access token");
    ResponseEntity<Map<String, String>> response = restTemplate.exchange(
        tokenUrl, HttpMethod.POST, getHttpEntity(), new ParameterizedTypeReference<>() {});
    if (response.getStatusCode() != HttpStatus.OK) {
      String message = "Unable to get an access token";
      log.error(message);
      throw new IllegalStateException(message + " " + response.getStatusCode());
    }
    return getTokenFromResponse(response);
  }

  private String getTokenFromResponse(ResponseEntity<Map<String, String>> response) {
    final String errorMessage = "Request was successful, but token was not received {}";
    final String key = "access_token";
    Map<String, String> responseBody = response.getBody();
    if (responseBody == null) {
      log.error(errorMessage, "(body is empty)");
    } else if (responseBody.get(key) == null) {
      log.error(errorMessage, "(no access token in response)");
    } else {
      return responseBody.get(key);
    }
    throw new IllegalStateException("Token has not been received");
  }
}
