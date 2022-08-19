package com.artzvrzn.store.classifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.artzvrzn.store.classifier.dao")
public class ClassifierApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClassifierApplication.class, args);
  }
}
