package com.meds.deliveries.controller;

import java.util.ArrayList;
import java.util.List;

import com.meds.deliveries.dto.UserDTO;
import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.model.Ride;
import com.meds.deliveries.repository.RideRepository;
import com.meds.deliveries.repository.RiderRepository;
import com.meds.deliveries.request.LoginRequest;
import com.meds.deliveries.request.MessageResponse;
import com.meds.deliveries.security.auth.AuthTokenResponse;
import com.meds.deliveries.service.SpringUserDetailsService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RiderControllerTest {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Rider rider;
    
    private Ride ride;

    private Package rider_package;
    
    private HttpEntity<String> jwtEntity;

    private String url;

    private HttpHeaders headers;

    @AfterEach
    public void tearDown() {
        riderRepository.deleteAll();
    }

    @BeforeAll
    void beforeAll() {
        restTemplate.postForEntity("/api/auth/register", new UserDTO("Artur", "artur01", "artur.romao@ua.pt", "artur123", 914914901, "My house"), MessageResponse.class);
        LoginRequest loginRequest = new LoginRequest("artur.romao@ua.pt", "artur123");
        ResponseEntity<AuthTokenResponse> loginResponse = restTemplate.postForEntity("/api/auth/login", loginRequest, AuthTokenResponse.class);
        AuthTokenResponse authTokenResponse = loginResponse.getBody();

        String token = "Bearer " + authTokenResponse.getToken();
        headers = new HttpHeaders();
        headers.set("Authorization", token);
        jwtEntity = new HttpEntity<String>(headers);
    }

    @BeforeEach
    void setUp() {
        url = "/api";
        rider = new Rider("John Doe", "johndoe", passwordEncoder.encode("12345678"), "john@doe.com", 912345678, new SimpleGrantedAuthority("deliveries"), "My house");
        rider_package = new Package("41.3221", "-37.2213", "My street", "John Doe", 1, 1);
        ride = new Ride(rider_package, 5);
        rideRepository.save(ride);
        List<Ride> rides = new ArrayList<Ride>();
        rides.add(ride);
        rider.setRides(rides);
        riderRepository.save(rider);
        System.out.println("RATA: " + rider.getId());
    }

/*     @Test
    void getAllRiders() {
        url += "/riders";
        ResponseEntity<List<Rider>> response = restTemplate
            .exchange(url, HttpMethod.GET, jwtEntity, new ParameterizedTypeReference<List<Rider>>() {
            });

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).extracting(Rider::getName).hasSize(1).doesNotContainNull();
            assertThat(response.getBody()).extracting(Rider::getName).containsOnly("John Doe");
            assertThat(response.getBody()).extracting(Rider::getUsername).containsOnly("johndoe");
            assertThat(response.getBody()).extracting(Rider::getEmail).containsOnly("john@doe.com");
            assertThat(response.getBody()).extracting(Rider::getPermission).containsOnly(rider.getPermission());
            assertThat(response.getBody()).extracting(Rider::getAddress).containsOnly("My house");
    }

    @Test
    void getRiderDetails() {
        url += "/rider/" + String.valueOf(rider.getId());
        ResponseEntity<Rider> response = restTemplate
            .exchange(url, HttpMethod.GET, jwtEntity, new ParameterizedTypeReference<Rider>() {
            });

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getName()).isEqualTo("John Doe");
            assertThat(response.getBody().getUsername()).isEqualTo("johndoe");
            assertThat(response.getBody().getEmail()).isEqualTo("john@doe.com");
            assertThat(response.getBody().getPermission()).isEqualTo(rider.getPermission());
            assertThat(response.getBody().getAddress()).isEqualTo("My house");
    } */

    @Test
    void getRiderSpecificPackage() {
        url += "/rider/" + String.valueOf(rider.getId()) + "/package/" + String.valueOf(rider_package.getId());
        ResponseEntity<Package> response = restTemplate
            .exchange(url, HttpMethod.GET, jwtEntity, new ParameterizedTypeReference<Package>() {
            });

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getId()).isEqualTo(rider_package.getId());
            assertThat(response.getBody().getStatus()).isEqualTo(rider_package.getStatus());
            assertThat(response.getBody().getClient_name()).isEqualTo("John Doe");
            assertThat(response.getBody().getClient_addr()).isEqualTo("My street");
    }

    @Test
    void getRiderListOfPackages() {
        url += "/rider/" + String.valueOf(rider.getId()) + "/packages";
        ResponseEntity<List<Package>> response = restTemplate
            .exchange(url, HttpMethod.GET, jwtEntity, new ParameterizedTypeReference<List<Package>>() {
            });

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody()).extracting(Package::getId).hasSize(1).doesNotContainNull();
            assertThat(response.getBody()).extracting(Package::getClient_addr).containsOnly("My street");
            assertThat(response.getBody()).extracting(Package::getClient_name).containsOnly("John Doe");
            assertThat(response.getBody()).extracting(Package::getOrder_id).containsOnly(1);
            assertThat(response.getBody()).extracting(Package::getRider_id).containsOnly(1);
            assertThat(response.getBody()).extracting(Package::getStore_id).containsOnly(1);
    }
}
