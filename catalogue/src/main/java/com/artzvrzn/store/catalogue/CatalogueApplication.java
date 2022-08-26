package com.artzvrzn.store.catalogue;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.artzvrzn.store.catalogue.client.api")
public class CatalogueApplication {

  public static void main(String[] args) {
    SpringApplication.run(CatalogueApplication.class, args);
  }
}
