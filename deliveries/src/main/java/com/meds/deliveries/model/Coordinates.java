package com.meds.deliveries.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;


import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "coordinates")
public class Coordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coordinates")
    private int id;

    @Column(name = "lat", nullable = false)
    private double lat;

    @Column(name = "lon", nullable = false)
    private double lon;

    @OneToOne(mappedBy = "store_location")
    private Store storeLocation;

      
    @OneToOne(mappedBy = "packageFinalLocation")
    private Package packageFinalLocation;
  

    @Autowired
    public Coordinates(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}