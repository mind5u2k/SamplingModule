package com.ison.app.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.ison")
public class ApplicationConfiguration {

  public static void main(String[] args) {	
    SpringApplication.run(ApplicationConfiguration.class, args);	
  }
}
