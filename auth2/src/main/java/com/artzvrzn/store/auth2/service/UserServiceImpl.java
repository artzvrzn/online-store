package com.artzvrzn.store.auth2.service;

import com.artzvrzn.store.auth2.domain.Role;
import com.artzvrzn.store.auth2.domain.User;
import com.artzvrzn.store.auth2.dto.request.UserRequest;
import com.artzvrzn.store.auth2.service.api.UserService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private ModelMapper modelMapper;
  Map<UUID, User> users = new HashMap<>();

  @Override
  public User create(UserRequest userRequest) {
    User user = modelMapper.map(userRequest, User.class);
    user.setId(UUID.randomUUID());
    user.setRoles(Collections.singleton(Role.ADMIN));
    users.put(user.getId(), user);
    return user;
  }

  @Override
  public User getById(UUID id) {
    return users.get(id);
  }

  @Override
  public User getByUsername(String username) {
    for (Map.Entry<UUID, User> entry: users.entrySet()) {
      if (entry.getValue() != null && entry.getValue().getUsername().equals(username)) {
        return entry.getValue();
      }
    }
    return null;
  }

  @Override
  public User update(UUID id, UserRequest userRequest) {
    return users.get(id);
  }

  @Override
  public void delete(UUID id) {
    users.remove(id);
  }
}
