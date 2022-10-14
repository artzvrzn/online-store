package com.artzvrzn.store.catalogue.controller.rest;

import com.artzvrzn.store.catalogue.dto.ItemDto;
import com.artzvrzn.store.catalogue.service.api.ItemService;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Sample {
  @Autowired
  private ItemService itemService;

  @PostMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
  public Object get(@PathVariable("id") UUID id, ItemDto dto) throws InterruptedException {
    Thread[] threads = new Thread[10];

    for (int i = 0; i < threads.length; i++) {
      Runnable runnable =   new Runnable() {

        @Override
        public void run() {
          try {
            Thread.sleep(new Random().nextInt(1000));
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          itemService.delete(id);
        }
      };
      DelegatingSecurityContextRunnable secRunnable = new DelegatingSecurityContextRunnable(runnable);
      threads[i] = new Thread(secRunnable);
    }
    for (Thread runnable: threads) {
      runnable.start();
    }
    for (Thread runnable: threads) {
      runnable.join();
    }
    return null;
  }
}
