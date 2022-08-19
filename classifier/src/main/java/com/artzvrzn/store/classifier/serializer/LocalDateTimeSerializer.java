package com.artzvrzn.store.classifier.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {


  @Override
  public void serialize(LocalDateTime ldt, JsonGenerator jg, SerializerProvider sp)
      throws IOException {
    long millis = ldt.toInstant(ZoneOffset.UTC).toEpochMilli();
    jg.writeNumber(millis);
  }
}
