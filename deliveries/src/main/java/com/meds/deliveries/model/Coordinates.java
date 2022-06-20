package com.meds.deliveries.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
  
    public Coordinates(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
    //public Coordinates(){}
}