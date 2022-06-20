package com.meds.deliveries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.meds.deliveries.model.Admin;
import com.meds.deliveries.service.AdminService;

@SpringBootApplication
public class DeliveriesApplication  {

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
