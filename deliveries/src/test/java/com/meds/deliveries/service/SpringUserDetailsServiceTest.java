package com.meds.deliveries.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.meds.deliveries.model.Person;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpringUserDetailsServiceTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private SpringUserDetailsService springUserDetailsService;

    private UserDetails userDetails;
    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Artur", "artur01", "artur123", "artur@ua.pt", 910471747, new SimpleGrantedAuthority("deliveries"));
        userDetails = new org.springframework.security.core.userdetails.User("artur01", "artur123", Collections.emptyList());
    }

    @Test
    @DisplayName("Load UserDetails instance by username")
    void loadUserByUsername() {

        when(personService.getPersonByUsername(person.getUsername())).thenReturn(person);

        assertThat(springUserDetailsService.loadUserByUsername(person.getUsername()))
                .isNotNull()
                .isEqualTo(userDetails);

        verify(personService, Mockito.times(1)).getPersonByUsername(Mockito.anyString());

    }

}
