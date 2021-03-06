package com.meds.deliveries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

import com.meds.deliveries.dto.UserDTO;
import com.meds.deliveries.exception.BadRequestException;
import com.meds.deliveries.model.Person;
import com.meds.deliveries.request.LoginRequest;
import com.meds.deliveries.request.MessageResponse;
import com.meds.deliveries.security.auth.AuthTokenResponse;
import com.meds.deliveries.security.auth.JWTTokenUtils;
import com.meds.deliveries.service.PersonService;
import com.meds.deliveries.service.RiderService;
import com.meds.deliveries.service.SpringUserDetailsService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/auth")
public class AuthController {
    
    private final PasswordEncoder passwordEncoder;
    private final RiderService riderService;
    private final PersonService personService;
    private final SpringUserDetailsService springUserDetailsService;
    private final JWTTokenUtils jwtTokenUtils;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, RiderService riderService, PersonService personService, SpringUserDetailsService springUserDetailsService, JWTTokenUtils jwtTokenUtils) {
        this.passwordEncoder = passwordEncoder;
        this.riderService = riderService;
        this.personService = personService;
        this.springUserDetailsService = springUserDetailsService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @PostMapping("/register")
    public MessageResponse registerRider(@RequestBody UserDTO userDTO) {

        riderService.registerRider(userDTO);
        return new MessageResponse(Date.from(Instant.now()), "The user was successfully registered!");

    }

    @PostMapping("/login")
    public AuthTokenResponse loginUser(@RequestBody LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (email == null || password == null)
            throw new BadRequestException("Please provide a valid request body.");

        Person person = personService.getPersonByEmail(email);
        UserDetails userDetails = springUserDetailsService.loadUserByUsername(person.getUsername());

        String encodedpw = passwordEncoder.encode(person.getPassword());
        if (!passwordEncoder.matches(password, encodedpw))
            throw new BadCredentialsException("The provided password is wrong.");
            
        String token = jwtTokenUtils.generateToken(userDetails);

        return new AuthTokenResponse("Authentication succeeded.", token);

    }

}
