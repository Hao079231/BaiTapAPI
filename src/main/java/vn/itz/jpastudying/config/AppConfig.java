package vn.itz.jpastudying.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.itz.jpastudying.model.HelloWorld;

@Configuration
public class AppConfig {
  @Bean
  public HelloWorld helloWorldBean(){
    HelloWorld helloWorld = new HelloWorld();
    helloWorld.setMessage("Hell World !!!!!");

    return helloWorld;
  }
}
