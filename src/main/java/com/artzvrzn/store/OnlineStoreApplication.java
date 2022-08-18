package com.artzvrzn.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.artzvrzn.store.dao")
public class OnlineStoreApplication {

  public static void main(String... args) {
    SpringApplication.run(OnlineStoreApplication.class, args);
  }


}
