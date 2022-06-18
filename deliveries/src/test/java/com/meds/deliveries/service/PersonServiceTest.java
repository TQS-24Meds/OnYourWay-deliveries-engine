package com.meds.deliveries.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.meds.deliveries.exception.DuplicatedObjectException;
import com.meds.deliveries.model.Person;
import com.meds.deliveries.repository.PersonRepository;

import java.util.Collections;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {


    @Mock(lenient = true)
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Bernardo Farias", "bernas", "supersecure", "bernoso@gmail.com", 965174856, Collections.emptyList());

    }

    @Test
    @DisplayName("Find person by username.")
    void findByUsername() {

        when(repository.findByUsername(person.getUsername())).thenReturn(person);
        when(repository.existsByUsername(person.getUsername())).thenReturn(true);

        assertThat(service.getPersonByUsername(person.getUsername()))
                .isNotNull()
                .isEqualTo(person);

        verify(repository, Mockito.times(1)).findByUsername(Mockito.anyString());
        verify(repository, Mockito.times(1)).existsByUsername(Mockito.anyString());  
    }

    @Test
    @DisplayName("Find person by non-existent username.")
    void findByNonExistentUsername() {
        
        when(repository.existsByUsername(person.getUsername())).thenReturn(false);
        assertThatThrownBy(() -> service.getPersonByUsername(person.getUsername()))
        .isInstanceOf(ResourceNotFoundException.class)
        .hasMessage("Person not found for this username:%s", person.getUsername());

        verify(repository, Mockito.times(0)).findByUsername(Mockito.anyString());
        verify(repository, Mockito.times(1)).existsByUsername(Mockito.anyString());

    }

    @Test
    @DisplayName("Find person by email.")
    void findByEmail() {

        when(repository.findByEmail(person.getEmail())).thenReturn(person);
        when(repository.existsByEmail(person.getEmail())).thenReturn(true);

        assertThat(service.getPersonByEmail(person.getEmail()))
                .isNotNull()
                .isEqualTo(person);

        verify(repository, Mockito.times(1)).findByEmail(Mockito.anyString());
        verify(repository, Mockito.times(1)).existsByEmail(Mockito.anyString());

    }

    @Test
    @DisplayName("Find person by non-existent email.")
    void findByNonExistentEmail() {

        when(repository.existsByEmail(person.getEmail())).thenReturn(false);

        assertThatThrownBy(() -> service.getPersonByEmail(person.getEmail()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Person not found for this email:%s", person.getEmail());

        verify(repository, Mockito.times(0)).findByEmail(Mockito.anyString());
        verify(repository, Mockito.times(1)).existsByEmail(Mockito.anyString());

    }

    @Test
    @DisplayName("Register already existent person.")
    void registerExistentPerson() {

        when(repository.existsByUsername(person.getUsername())).thenReturn(true);

        assertThrows(DuplicatedObjectException.class, () -> service.registerPerson(person));

    }
    
}
