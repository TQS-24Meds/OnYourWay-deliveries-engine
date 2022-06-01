package com.meds.deliveries.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Optional;

import com.meds.deliveries.model.Person;

import static org.hamcrest.CoreMatchers.nullValue;

@DataJpaTest
public class PersonRepositoryTest {
    
    @Autowired private TestEntityManager entityManager;

    @Autowired private PersonRepository repository;

    @Test
    public void whenFindPersonByExistingId_thenReturnPerson() {
        Person john = new Person("John Doe", "johndoe", "mypassword", "john@doe.com", "Some address", 912345678);
        entityManager.persistAndFlush(john);

        Person found = repository.findById(john.getId());
        assertThat( found, is(john) );
    }


    @Test
    public void whenFindPersonByInvalidId_thenReturnNull() {
        int invalidId = -1;
        Person found = repository.findById(invalidId);
        assertThat( found, is(nullValue()) );
    }

    @Test
    public void whenFindPersonByValidUsername_thenReturnValidPerson() {
        Person john = new Person("John Doe", "johndoe", "mypassword", "john@doe.com", "Some address", 912345678);
        entityManager.persistAndFlush(john);

        Optional<Person> found = repository.findByUsername(john.getUsername());
        found.ifPresent(person -> assertThat( person, is(john) ) );
    }

    @Test
    public void whenFindPersonByInvalidUsername_thenReturnNull() {
        String invalidPersonname = "ThisPersonnameDoesNotExist";
        Optional<Person> found = repository.findByUsername(invalidPersonname);
        assertThat( found.isPresent(), is(false) );
    }

    @Test
    public void whenFindByValidPhoneNumber_thenReturnValidPerson() {
        Person john = new Person("John Doe", "johndoe", "mypassword", "john@doe.com", "Some address", 912345678);
        entityManager.persistAndFlush(john);

        Person found = repository.findByPhone(john.getPhone());
        assertThat( found, is(john) );
    }

    @Test
    public void whenFindPersonByInvalidPhoneNumber() {
        int invalidPhoneNumber = 1234;
        Person found = repository.findByPhone(invalidPhoneNumber);
        assertThat( found, is(nullValue()) );
    }

}
