package com.meds.deliveries.repository;

import java.util.Optional;

import com.meds.deliveries.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);
    Optional<Person> findByEmail(String email);
    Optional<Person> findByPhone(int phone);
    Optional<Person> findById(int id);
}
