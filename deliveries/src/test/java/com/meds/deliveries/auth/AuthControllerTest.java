package com.meds.deliveries.auth;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.meds.deliveries.exception.ErrorDetails;
import com.meds.deliveries.model.Person;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.repository.PersonRepository;
import com.meds.deliveries.repository.RiderRepository;
import com.meds.deliveries.request.LoginRequest;
import com.meds.deliveries.request.MessageResponse;
import com.meds.deliveries.security.auth.AuthTokenResponse;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
class AuthControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RiderRepository riderRepository;

    private Person person;

    private Rider rider;

    @AfterEach
    public void tearDown() {
        riderRepository.deleteAll();
        personRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
        rider = new Rider("John Doe", "johndoe", "mypassword", "john@doe.com", 912345678, Collections.emptyList(), "My house");
        person = new Person("Francis Jane", "francisjane", "jasonderulo", "francis@jane.com", 914914901, Collections.emptyList());
    }

    @Test
    void registerRider() {

        ResponseEntity<MessageResponse> response = restTemplate.postForEntity("/api/auth/register", rider, MessageResponse.class);

        List<Rider> riders = riderRepository.findAll();

        assertThat(riderRepository.findByUsername("johndoe")).isNotNull();
        assertThat(riders).hasSize(1).doesNotContainNull();
        assertThat(riders).extracting(Rider::getUsername).containsOnly("johndoe");
        assertThat(riders).extracting(Rider::getEmail).containsOnly("john@doe.com");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(MessageResponse::getMessage).isEqualTo("The user was successfully registered!");

    }

    @Test
    @DisplayName("Login with correct credentials")
    void loginUser() {

        restTemplate.postForEntity("/api/auth/register", person, MessageResponse.class);

        LoginRequest loginRequest = new LoginRequest(person.getEmail(), person.getPassword());

        ResponseEntity<AuthTokenResponse> response = restTemplate.postForEntity("/api/auth/login", loginRequest, AuthTokenResponse.class);
        AuthTokenResponse authTokenResponse = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(authTokenResponse).isNotNull();
        assertThat(authTokenResponse).extracting(AuthTokenResponse::getToken).isNotNull();
        assertThat(authTokenResponse).extracting(AuthTokenResponse::getMessage).isEqualTo("Authentication succeeded.");

    }

    @Test
    @DisplayName("Login with wrong credentials")
    void loginWithWrongCredentials() {

        restTemplate.postForEntity("/api/auth/register", person, MessageResponse.class);

        LoginRequest loginRequest = new LoginRequest(person.getEmail(), "wrong_password");

        ResponseEntity<ErrorDetails> response = restTemplate.postForEntity("/api/auth/login", loginRequest, ErrorDetails.class);
        ErrorDetails errorDetailsResponse = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(errorDetailsResponse).isNotNull();
        assertThat(errorDetailsResponse).extracting(ErrorDetails::getTimestamp).isNotNull();
        assertThat(errorDetailsResponse).extracting(ErrorDetails::getMessage).isEqualTo("The provided password is wrong.");

    }

    @Test
    @DisplayName("Login with wrong body request")
    void loginWithWrongBodyRequest() {

        LoginRequest loginRequest = new LoginRequest(null, null);

        ResponseEntity<ErrorDetails> response = restTemplate.postForEntity("/api/auth/login", loginRequest, ErrorDetails.class);
        ErrorDetails errorDetailsResponse = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errorDetailsResponse).isNotNull();
        assertThat(errorDetailsResponse).extracting(ErrorDetails::getTimestamp).isNotNull();
        assertThat(errorDetailsResponse).extracting(ErrorDetails::getMessage).isEqualTo("Please provide a valid request body.");

    }

}
