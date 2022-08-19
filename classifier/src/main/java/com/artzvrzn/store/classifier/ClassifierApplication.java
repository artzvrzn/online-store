package com.artzvrzn.store.classifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.artzvrzn.store.classifier.dao")
public class ClassifierApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClassifierApplication.class, args);
  }
}
