package com.artzvrzn.store.auth.service;

import com.artzvrzn.store.auth.dao.RoleRepository;
import com.artzvrzn.store.auth.dao.UserRepository;
import com.artzvrzn.store.auth.domain.Role;
import com.artzvrzn.store.auth.domain.Status;
import com.artzvrzn.store.auth.domain.User;
import com.artzvrzn.store.auth.dto.UserDto;
import com.artzvrzn.store.auth.service.api.IUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService implements IUserService {
  private final static String USER_ROLE = "ROLE_USER";
  private final static String ADMIN_ROLE = "ROLE_ADMIN";

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private ModelMapper mapper;

  @PostConstruct
  private void postConstruct() {
    if (!roleRepository.isMainRolesPresent()) {
      Role userRole = new Role(USER_ROLE);
      Role adminRole = new Role(ADMIN_ROLE);
      roleRepository.saveAll(List.of(userRole, adminRole));
    }
    if (userRepository.findByUsername("admin").isEmpty()) {
      UserDto userDto = new UserDto();
      userDto.setPassword("123");
      userDto.setEmail("admin@email.com");
      userDto.setUsername("admin");
      userDto.setLastName("admin");
      userDto.setLastName("admin");
      userDto.setStatus(Status.ACTIVE);
      User user = mapper.map(userDto, User.class);
      user.setId(UUID.randomUUID());
      user.setPassword(passwordEncoder.encode(userDto.getPassword()));
      List<Role> roles = user.getRoles();
      List<Role> roleList = roleRepository.findAll();
      roles.addAll(roleList);
      userRepository.save(user);
    }
  }

  @Override
  public UserDto create(UserDto dto) {
    dto.setId(UUID.randomUUID());
    dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    User user = mapper.map(dto, User.class);
    user.getRoles().add(roleRepository.getReferenceById(USER_ROLE));
    user.setStatus(Status.ACTIVE);
    userRepository.save(user);
    return dto;
  }

  @Override
  public UserDto get(UUID id) {
    User user = userRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException(String.format("User with id %s doesn't exist", id)));
    return mapper.map(user, UserDto.class);
  }

  @Override
  public UserDto get(String username) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new IllegalArgumentException(
            String.format("User with username %s doesn't exist", username)));
    return mapper.map(user, UserDto.class);
  }

  @Override
  public Page<UserDto> get(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
    return userRepository.findAll(pageable).map(e -> mapper.map(e, UserDto.class));
  }

  @Override
  public UserDto update(UUID id) {
    return null;
  }

  @Override
  public void delete(UUID id) {

  }
}
