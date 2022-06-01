package com.meds.deliveries.model;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends Person {

    public Admin(){}

    public Admin(String name, String username, String password, String email, String address, int phone){
        super(name, username, password, email, address, phone);
    }
}
