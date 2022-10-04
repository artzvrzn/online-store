package com.artzvrzn.store.catalogue.config.audit;

import com.artzvrzn.store.catalogue.domain.UserInfo;
import com.artzvrzn.store.catalogue.service.api.UserService;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;

@RequiredArgsConstructor
final class UUIDAuditorAware implements AuditorAware<UUID> {
  private final UserService userService;

  @Override
  public Optional<UUID> getCurrentAuditor() {
    UserInfo userInfo = userService.getAuthenticatedUser();
    if (userInfo != null && userInfo.getUserId() != null) {
      return Optional.ofNullable(userInfo.getUserId());
    }
    return Optional.empty();
  }
}
