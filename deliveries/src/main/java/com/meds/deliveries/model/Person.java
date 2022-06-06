package com.meds.deliveries.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @Size(min = 8)
    private String password;

    @Email
    @Column(name = "email", nullable = false, unique=true)
    private String email;

    @Column(name = "phone", nullable = false)
    private int phone;

    // @OneToOne(mappedBy = "rider", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonIgnore
    // private Rider rider;

    // @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonIgnore
    // private Admin admin;

    public Person(String name, String username, String password, String email, int phone) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

}