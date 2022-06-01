package com.meds.deliveries.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @Size(min = 8)
    private String password;

    @Email
    @Column(name = "email", nullable = false, unique=true)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private int phone;


    public Person(String name, String username, String password, String email, String address, int phone) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

}
