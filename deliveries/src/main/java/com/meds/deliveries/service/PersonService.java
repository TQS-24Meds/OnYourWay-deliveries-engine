package com.meds.deliveries.service;

import com.meds.deliveries.model.Person;
import com.meds.deliveries.repository.PersonRepository;

import lombok.NonNull;

import com.meds.deliveries.exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonService {
    
    @Autowired PersonRepository repository;

    public void registerPerson(Person p) throws DuplicatedObjectException {
        if (repository.existsByUsername(p.getUsername()))
            throw new DuplicatedObjectException("The provided username is already taken.");
        repository.save(p);    
    }

    public Person getPersonById(int id) throws ResourceNotFoundException {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Person not found for this id:" + id);
        }
        return repository.findById(id);

    }

    @NonNull
    public Person getPersonByUsername(String username) {
        if (!repository.existsByUsername(username)) throw new ResourceNotFoundException("Person not found for this username:" + username);
        
        return repository.findByUsername(username);
    }

    @NonNull
    public Person getPersonByEmail(String email) {
        if (!repository.existsByEmail(email)) {
            throw new ResourceNotFoundException("Person not found for this email:" + email);
        }
        return repository.findByEmail(email);
    }

    public Map<String, Boolean> deletePerson(Person Person) throws ResourceNotFoundException {

        int id = Person.getId();

        if (!repository.existsById(id)) {
            
            throw new ResourceNotFoundException("Person not found for this id:" + id);
        }

        repository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public Person updatePerson(Person person) throws ResourceNotFoundException {
        int id = person.getId();
        if (!repository.existsById(id)) {
            System.out.println("cant found");
            throw new ResourceNotFoundException("Person not found for this id:" + id);
        }

        Person existingPerson = repository.findById(id);

        existingPerson.setName(person.getName());
        existingPerson.setEmail(person.getEmail());
        existingPerson.setPhone(person.getPhone());
        existingPerson.setUsername(person.getUsername());
        existingPerson.setPassword(person.getPassword());

        return repository.save(existingPerson);
    }
}
