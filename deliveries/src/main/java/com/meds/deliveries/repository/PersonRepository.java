package com.meds.deliveries.repository;


import com.meds.deliveries.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByUsername(String username);
    Person findByEmail(String email);
    Person findByPhone(int phone);
    Person findById(int id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
