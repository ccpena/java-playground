package org.kkpa.playgroundsb.config;

import components.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public UserFacade userFacade() {
    return new UserFacade();
  }
}
