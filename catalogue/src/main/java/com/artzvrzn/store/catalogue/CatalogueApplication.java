package com.artzvrzn.store.catalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.artzvrzn.store.catalogue.client.api")
@EnableJpaRepositories(basePackages = "com.artzvrzn.store.catalogue.dao.api")
public class CatalogueApplication {

  public static void main(String[] args) {
    SpringApplication.run(CatalogueApplication.class, args);
  }
}
