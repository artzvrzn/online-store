package com.artzvrzn.store.classifier.controller.rest;

import com.artzvrzn.store.classifier.model.Currency;
import com.artzvrzn.store.classifier.service.api.ICurrencyService;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

  @Autowired
  private ICurrencyService currencyService;

  @GetMapping(
      value = {"/{name}", "/{name}/"},
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public Currency get(@PathVariable("name") String name) {
    return currencyService.get(name.toUpperCase());
  }

  @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<Currency> getAll() {
    return currencyService.getAll();
  }

  @RequestMapping(value = {"/{name}", "/{name}/"}, method = RequestMethod.HEAD)
  public ResponseEntity<?> isExist(@PathVariable("name") String name) {
    return currencyService.isExist(name.toUpperCase()) ?
        ResponseEntity.ok().build() : ResponseEntity.noContent().build();
  }

  @PostMapping(
      value = {"/", ""},
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public Currency create(@RequestBody @Valid Currency currency) {
    return currencyService.create(currency);
  }

  @PutMapping(
      value = {"/{name}/{updated}", "/{name}/{updated}/"},
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public Currency update(
      @RequestBody Currency currency,
      @PathVariable("name") String name,
      @PathVariable("updated") LocalDateTime updated
  ) {
    return currencyService.update(currency, name.toUpperCase(), updated);
  }
}
