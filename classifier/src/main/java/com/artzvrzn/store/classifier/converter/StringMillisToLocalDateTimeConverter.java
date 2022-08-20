package com.artzvrzn.store.classifier.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringMillisToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

  @Override
  public LocalDateTime convert(String millis) {
    long valueOf = Long.parseLong(millis);
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(valueOf), ZoneOffset.UTC);
  }
}
