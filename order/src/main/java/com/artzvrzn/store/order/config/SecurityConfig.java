package com.artzvrzn.store.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
//                .mvcMatchers("/test").hasAuthority("SCOPE_store.read")
                .mvcMatchers("/test").permitAll()
                .anyRequest().authenticated()
        )
        .oauth2ResourceServer()
        .jwt();
    return http.build();
  }
}
