package com.artzvrzn.store.catalogue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeRequests()
      .antMatchers("/backend/**").access("hasAuthority('SCOPE_system')")
      .and()
      .authorizeRequests()
      .anyRequest().permitAll()
      .and()
      .oauth2ResourceServer()
      .jwt();
    return http.build();
  }
}
