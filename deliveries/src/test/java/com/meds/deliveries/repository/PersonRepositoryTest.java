package com.meds.deliveries.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.meds.deliveries.model.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest extends RunTestContainer {
    
    @Autowired private TestEntityManager entityManager;

    @Autowired private PersonRepository repository;

    Person john;

    @BeforeEach
    void setUp() {
        john = new Person("John Doe", "johndoe", "johnpass", "john@doe.com", 911111111, Collections.emptyList());
        entityManager.persistAndFlush(john);
    }

    @Test
    public void whenFindPersonByExistingId_thenReturnPerson() {
        Person personFound = repository.findById(john.getId()).orElse(null);;
        assertThat( personFound ).isEqualTo( john );
    }

    @Test
    public void whenFindPersonByInvalidId_thenReturnNull() {
        int invalidId = -1;
        Person personFound = repository.findById(invalidId).orElse(null);;
        assertThat( personFound ).isNull();
    }

    @Test
    public void whenFindPersonByValidUsername_thenReturnValidPerson() {
        Person personFound = repository.findByUsername(john.getUsername()).orElse(null);;
        assertThat( personFound ).isEqualTo( john );
    }

    @Test
    public void whenFindPersonByInvalidUsername_thenReturnNull() {
        String invalidUsername = "anndoe";
        Person personFound = repository.findByUsername(invalidUsername).orElse(null);;
        assertThat( personFound ).isNull();
    }

    @Test
    public void whenFindByValidPhone_thenReturnValidPerson() {
        Person personFound = repository.findByPhone(john.getPhone()).orElse(null);;
        assertThat( personFound ).isEqualTo( john );
    }

    @Test
    public void whenFindPersonByInvalidPhone() {
        int invalidPhone = 1234;
        Person personFound = repository.findByPhone(invalidPhone).orElse(null);;
        assertThat( personFound ).isNull();
    }

}
