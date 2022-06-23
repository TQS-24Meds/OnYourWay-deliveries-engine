package com.meds.deliveries;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.meds.deliveries.model.*;
import com.meds.deliveries.repository.*;

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

@Profile("!test")
@Component
class DBLoader implements CommandLineRunner {

	@Autowired AdminRepository adminRepository;
	
	@Autowired RiderRepository riderRepository;

	@Autowired StoreRepository storeRepository;

	@Autowired CoordinatesRepository coordinatesRepository;

    @Autowired PasswordEncoder passwordEncoder;


	public void run(String... args) throws Exception {

        if (storeRepository.findAll().isEmpty()) {

            Coordinates store_coor = new Coordinates(-39.80711, 151.71425);
            coordinatesRepository.save(store_coor);
            
            Store meds = new Store(
                "24Meds", UUID.randomUUID(), 
                store_coor);
            storeRepository.saveAndFlush(meds);
        }

        coordinatesRepository.flush();

        if (adminRepository.findAll().isEmpty()) {
            Admin admin = new Admin(
                "John Doe",
                "johndoe",
                passwordEncoder.encode("adminpassword"),
                "admin@email.com",
                911333555,
                "management");

                adminRepository.saveAndFlush(admin);
        }

        if (riderRepository.findAll().isEmpty()) {
            Rider r1 = new Rider(
                "John Castle",
                "joncastle",
                passwordEncoder.encode("johnpassword"),
                "john@castle.com",
                911222333,
                "deliveries",
                "The Sun");

            Rider r2 = new Rider(
                "Susan Boyle",
                "susanneye",
                passwordEncoder.encode("strongpasswortd"),
                "susan@boyle.com",
                987657432,
                "deliveries",
                "The Moon");

            Rider r3 = new Rider(
                "Ann Treasure",
                "treasureann",
                passwordEncoder.encode("annnnnnn-a"),
                "nn@rider.com",
                911222333,
                "deliveries",
                "Pale Street");

                riderRepository.saveAndFlush(r1);
                riderRepository.saveAndFlush(r2);
                riderRepository.saveAndFlush(r3);
        }
    }
}

