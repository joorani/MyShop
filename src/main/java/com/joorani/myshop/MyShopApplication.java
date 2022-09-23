package com.joorani.myshop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MyShopApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyShopApplication.class, args);
    log.debug("logging test = {}", MyShopApplication.class);
  }

}
