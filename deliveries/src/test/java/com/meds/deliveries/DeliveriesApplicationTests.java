package com.meds.deliveries;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
class DeliveriesApplicationTests {

	static final MySQLContainer<?> container;

    static {
        container = 
         new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
          .withDatabaseName("OnYourWay")
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

	@Test
	void contextLoads() {
	}

}
