package com.artzvrzn.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableRedisRepositories
//@EnableJpaRepositories
public class Auth2Application {

  public static void main(String[] args) {
    SpringApplication.run(Auth2Application.class, args);
  }
}