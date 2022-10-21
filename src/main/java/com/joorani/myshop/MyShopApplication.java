package com.joorani.myshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class MyShopApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyShopApplication.class, args);

  }

}
