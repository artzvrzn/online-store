package com.artzvrzn.store.catalogue.config.audit;

import com.artzvrzn.store.catalogue.service.api.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "dateTimeProvider")
@RequiredArgsConstructor
public class AuditConfig {
  private final UserService userService;

  @Bean
  AuditorAware<UUID> auditorProvider() {
    return new UUIDAuditorAware(userService);
  }

  @Bean
  DateTimeProvider dateTimeProvider() {
    return new LocalDateTimeProvider();
  }
}
