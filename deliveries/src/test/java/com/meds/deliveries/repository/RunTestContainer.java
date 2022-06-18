package com.meds.deliveries.repository;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;


public abstract class RunTestContainer {

    static final MySQLContainer<?> container;

    static {
        container = 
         new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
          .withDatabaseName("24Meds")
          .withUsername("user")
          .withPassword("user")
          .withReuse(true);
    
        container.start();
      }
    
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
