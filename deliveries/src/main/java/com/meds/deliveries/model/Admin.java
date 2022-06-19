package com.meds.deliveries.model;

import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "admin")
public class Admin extends Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_admin")
    private int id;

    @Builder
    public Admin(String name, String username, String password, String email, int phone, List<GrantedAuthority> permissions) {
        super(name, username, password, email, phone, permissions);
    }
    
}
