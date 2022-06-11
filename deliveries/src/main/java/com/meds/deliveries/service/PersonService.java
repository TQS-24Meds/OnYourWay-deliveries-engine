package com.meds.deliveries.service;

import com.meds.deliveries.model.Person;
import com.meds.deliveries.repository.PersonRepository;
import com.meds.deliveries.exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public Person savePerson(Person Person) {
        return repository.save(Person);
    }

    public List<Person> savePersons(List<Person> Persons) {
        return repository.saveAll(Persons);
    }

    public List<Person> getPersons() {
        return repository.findAll();
    }

    public Person getPersonById(int id) throws ResourceNotFoundException {

        Person p = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found for this username:" + id));
        return p;
        
    }
    
    public Person getPersonByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Person not found for this username:" + username));
    }

    public Map<String, Boolean> deletePerson(Person Person) throws ResourceNotFoundException {

        int id  = Person.getId();

        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found for this id:" + id));
    
        repository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;  
    }

    public Person updatePerson(Person person) throws ResourceNotFoundException {
        int id = person.getId();
        Person existingPerson = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id:" + id));

        existingPerson.setName(person.getName());
        existingPerson.setEmail(person.getEmail());
        existingPerson.setPhone(person.getPhone());
        existingPerson.setUsername(person.getUsername());
        existingPerson.setPassword(person.getPassword());


        return repository.save(existingPerson);
    }
}
