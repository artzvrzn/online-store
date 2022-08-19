package com.artzvrzn.store.classifier.scheduled;

import com.artzvrzn.store.classifier.model.Currency;
import com.artzvrzn.store.classifier.model.ExchangeRate;
import com.artzvrzn.store.classifier.model.constant.BasicMessages;
import com.artzvrzn.store.classifier.service.api.ICurrencyService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
@Log4j2
public class RateUpdater {

  @Value("${currency-rates-api.url}")
  private String currencyRatesApiUrl;
  @Value("${currency-rates-api.base}")
  private String baseCurrencyName;
  @Value("${currency-rates-api.api-key}")
  private String apiKey;
  @Autowired
  private ICurrencyService currencyService;
  @Autowired
  private RestTemplate restTemplate;

  @Scheduled(
      cron = "${currency-rates-api.exchange-rate.cron}",
      zone = "${currency-rates-api.exchange-rate.timezone}"
  )
  public void updateRates() {
    List<Currency> currencies = currencyService.getAll();

    String url = currencies
        .stream()
        .map(Currency::getName)
        .filter(id -> !baseCurrencyName.equals(id))
        .collect(Collectors.joining(",", currencyRatesApiUrl, ""));

    ExchangeRate exchangeRate;
    try {
      exchangeRate = getRates(url);
    } catch (HttpStatusCodeException exc) {
      log.error(
          BasicMessages.LOG_HTTP_REQUEST_ERROR.getMessage(),
          "GET",
          url,
          exc.getStatusCode(),
          exc.getStatusText(),
          exc.getMessage());
      return;
    }
    log.info(BasicMessages.LOG_REQUEST_SUCCESS.getMessage(), "GET", url, exchangeRate);
    currencyService.updateRates(exchangeRate.getRates());
  }

  private ExchangeRate getRates(String url) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("apikey", apiKey);
    HttpEntity<ExchangeRate> httpEntity = new HttpEntity<>(httpHeaders);
    ResponseEntity<ExchangeRate> responseEntity =
        restTemplate.exchange(url, HttpMethod.GET, httpEntity, ExchangeRate.class);
    return responseEntity.getBody();
  }
}
