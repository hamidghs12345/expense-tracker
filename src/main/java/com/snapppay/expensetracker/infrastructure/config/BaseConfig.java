package com.snapppay.expensetracker.infrastructure.config;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {

  @Bean
  public Random random() {
    return new Random();
  }


}
