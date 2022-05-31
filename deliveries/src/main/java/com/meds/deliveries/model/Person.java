package com.meds.deliveries.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "person")
public class Person {

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


    public Person() {}

    public Person(String name, String username, String password, String email, String address, int phone) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return this.address; }

    public void setAddress(String address) { this.address = address; }

    public int getPhone() { return this.phone; }

    public void setPhone(int phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            "}";
    }

}
