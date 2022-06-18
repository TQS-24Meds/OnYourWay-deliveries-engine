package com.meds.deliveries.geocode.geocoding;

import javax.persistence.*;

import org.bouncycastle.crypto.util.Pack;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.model.Rider;

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

    @OneToOne(mappedBy = "packageLocation")
    private Package packageLocation;

    @OneToOne(mappedBy = "riderLocation")
    private Rider riderLocation;



    @Autowired
    public Coordinates( double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}