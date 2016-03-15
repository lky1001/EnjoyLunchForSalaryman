package com.soundlly.enjoylaunchforsalaryman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by soundllydev on 2016. 3. 15..
 */
@EnableAutoConfiguration
@ComponentScan
public class Application {

  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
  }
}
