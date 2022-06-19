package com.meds.deliveries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DeliveriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveriesApplication.class, args);
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080", "192.168.160.238:6768")
                        .allowedMethods("GET", "POST","PUT", "DELETE");
            }

        };
    }

}
