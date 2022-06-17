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
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "admin")
public class Admin extends Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_admin")
    private int id;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "admin")
    private List<Store> stores;

    
    // @NotNull
    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_person_admin", referencedColumnName = "id_person") 
    // @JsonIgnore
    // private Person admin;

    @Builder
    public Admin(String name, String username, String password, String email, int phone, GrantedAuthority permission) {
        super(name, username, password, email, phone, permission);
    }
    
}
