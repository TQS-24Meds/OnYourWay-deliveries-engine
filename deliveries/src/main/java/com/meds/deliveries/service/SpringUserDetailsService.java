package com.meds.deliveries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<GrantedAuthority> permissions = new ArrayList<GrantedAuthority>();
        permissions.add(new SimpleGrantedAuthority(person.getPermission()));
        return new org.springframework.security.core.userdetails.User(person.getUsername(), person.getPassword(), permissions);
    }

}
