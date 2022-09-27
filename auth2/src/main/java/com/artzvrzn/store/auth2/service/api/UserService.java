package com.artzvrzn.store.auth2.service.api;

import com.artzvrzn.store.auth2.domain.User;
import com.artzvrzn.store.auth2.dto.request.UserRequest;
import java.util.UUID;

public interface UserService {

  User create(UserRequest userRequest);

  User getById(UUID id);

  User getByUsername(String username);

  User update(UUID id, UserRequest userRequest);

  void delete(UUID id);
}
