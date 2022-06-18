package com.meds.deliveries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.meds.deliveries.model.Person;

@Service
public class SpringUserDetailsService implements UserDetailsService {

    private final PersonService personService;

    @Autowired
    public SpringUserDetailsService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Person person = personService.getPersonByUsername(username);
        return new org.springframework.security.core.userdetails.User(person.getUsername(), person.getPassword(), person.getPermissions());
    }

}
