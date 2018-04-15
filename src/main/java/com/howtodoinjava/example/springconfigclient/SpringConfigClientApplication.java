package com.howtodoinjava.example.springconfigclient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringConfigClientApplication {

  public static void main(String[] args) throws UnknownHostException {
    //SpringApplication.run(SpringConfigClientApplication.class, args);

    ConfigurableApplicationContext context = SpringApplication
        .run(SpringConfigClientApplication.class, args);

    Environment env = context.getEnvironment();
    String protocol = "http";
    if (env.getProperty("server.ssl.key-store") != null) {
      protocol = "https";
    }

    System.out.println(env.getProperty("spring.application.name"));
    System.out.println(env.getProperty("server.port"));
    System.out.println(InetAddress.getLocalHost().getHostAddress());
    System.out.println(env.getProperty("server.port"));
    System.out.println(env.getActiveProfiles());
    System.out.println(env.getProperty("spring.profiles.active"));
    System.out.println(env.getProperty("spring.profiles.active"));
    System.out.println(env.getProperty("spring.profiles.active"));


  }
}

@RefreshScope
@RestController
class MessageRestController {

  @Value("${msg:Hello world - Config Server is not working..please check}")
  private String msg;

  @RequestMapping("/msg")
  String getMsg() {
    return this.msg;
  }
}
