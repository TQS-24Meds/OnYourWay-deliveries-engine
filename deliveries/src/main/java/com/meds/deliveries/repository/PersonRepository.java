package com.meds.deliveries.repository;


import com.meds.deliveries.model.Person;

import lombok.NonNull;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @NonNull Person findByUsername(String username);
    @NonNull Person findByEmail(String email);
    Optional<Person> findByPhone(int phone);
    Person findById(int id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
