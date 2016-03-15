package com.soundlly.enjoylaunchforsalaryman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by soundllydev on 2016. 3. 15..
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
  }
}
