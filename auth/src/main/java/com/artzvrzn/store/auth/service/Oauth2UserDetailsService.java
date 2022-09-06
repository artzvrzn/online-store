package com.artzvrzn.store.auth.service;

import com.artzvrzn.store.auth.dao.UserRepository;
import com.artzvrzn.store.auth.domain.Oauth2User;
import com.artzvrzn.store.auth.domain.User;
import com.artzvrzn.store.auth.dto.LoginDto;
import com.artzvrzn.store.auth.dto.UserDto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Oauth2UserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
    return new Oauth2User(user);
  }

  public Authentication authenticate(LoginDto loginDto) {
    User user = userRepository.findByUsername(loginDto.getUsername())
        .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Wrong credentials"));
    if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
      Oauth2User oauth2User = new Oauth2User(user);
      return UsernamePasswordAuthenticationToken.authenticated(new Oauth2User(user), user.getPassword(), oauth2User.getAuthorities());
    }
    throw new AuthenticationCredentialsNotFoundException("wrong password");
  }

  private Map<String, Object> getClaims(UserDto userDto) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", userDto.getId());
    claims.put("username", userDto.getUsername());
    claims.put("firstName", userDto.getFirstName());
    claims.put("lastName", userDto.getLastName());
    claims.put("status", userDto.getStatus());
    claims.put("email", userDto.getEmail());
    claims.put("phone", userDto.getPhone());
    claims.put("country", userDto.getCountry());
    claims.put("city", userDto.getCity());
    claims.put("street", userDto.getStreet());
    claims.put("apartment", userDto.getApartment());
    return claims;
  }

}
