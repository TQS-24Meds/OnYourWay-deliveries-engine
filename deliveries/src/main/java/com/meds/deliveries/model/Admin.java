package com.meds.deliveries.model;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "admin")
public class Admin extends Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_admin")
    private int id;
    
    // @NotNull
    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_person_admin", referencedColumnName = "id_person") 
    // @JsonIgnore
    // private Person admin;

    @Builder
    public Admin(String name, String username, String password, String email, int phone) {
        super(name, username, password, email, phone);
    }
    
}