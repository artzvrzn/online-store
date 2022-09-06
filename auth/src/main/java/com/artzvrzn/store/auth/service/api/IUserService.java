package com.artzvrzn.store.auth.service.api;

import com.artzvrzn.store.auth.dto.UserDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface IUserService {

  UserDto create(UserDto dto);

  UserDto get(UUID id);

  UserDto get(String username);

  Page<UserDto> get(int page, int size);

  UserDto update(UUID id);

  void delete(UUID id);
}
