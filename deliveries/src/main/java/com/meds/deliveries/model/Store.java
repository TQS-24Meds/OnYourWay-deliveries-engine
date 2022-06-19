package com.meds.deliveries.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.*;


import lombok.*;


@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "store")
public class Store {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_store")
    private int id;

    @Column(name="name", nullable = false)
    private String name;
    
    @Column(name="storeuuid", nullable = false)
    private UUID storeuiid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_store")
    private Coordinates store_location;
    
    @OneToMany(mappedBy = "store")
    private Set<Package> packages;
    

    public Store(String name, UUID storeuuid, Coordinates coords){
        this.name = name;
        this.storeuiid = storeuuid;
    }


    
}
