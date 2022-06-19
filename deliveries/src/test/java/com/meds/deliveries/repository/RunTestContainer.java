package com.meds.deliveries.repository;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Testcontainers
public abstract class RunTestContainer {
  
  @Container
  static final MySQLContainer<?> container =
         new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
          .withDatabaseName("OnYourWay")
          .withUsername("user")
          .withPassword("user");
  static {
      container.start();
      // make sure that containers will be stop in fast way (Ryuk can be slow)
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        log.info("DockerContainers stop");
        container.stop();
    }));
  }
    
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
