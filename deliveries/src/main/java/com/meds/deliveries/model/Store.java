package com.meds.deliveries.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meds.deliveries.enums.RiderStatusEnum;
import com.meds.deliveries.geocode.geocoding.Coordinates;

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

    @ManyToOne
    @JoinColumn(name = "id_admin", nullable = false)
    private Admin admin;

    @OneToMany(mappedBy = "store")
    private Set<Package> packages;


    public Store(String name, UUID storeuuid, Coordinates coords, Admin admin){
        this.name = name;
        this.admin = admin;
        this.storeuiid = storeuuid;
    }


    
}
