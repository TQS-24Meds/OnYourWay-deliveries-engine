package com.meds.deliveries.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meds.deliveries.enums.RiderStatusEnum;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "rider")
public class Rider extends Person {
    
    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    // @Column(name = "id_rider")
    // private int id;

    @Column(name = "lat", nullable = false)
    private float lat;

    @Column(name = "lon", nullable = false)
    private float lon;

    @Column(name = "average_rating", nullable = false)
    private String average_rating;

    @Column(name = "num_reviews", nullable = false)
    private int num_reviews;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RiderStatusEnum status;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "rider")
    @JsonIgnore
    private Set<Ride> rides;

    // Unavailable
    public Rider(String name, String username, String password, String email, String address, int phone, String average_rating, int num_reviews, RiderStatusEnum status, Set<Ride> rides) {
        super(name, username, password, email, address, phone);
        this.average_rating = average_rating;
        this.num_reviews = num_reviews;
        this.status = RiderStatusEnum.UNAVAILABLE;
        this.rides = rides;
    }

    // Available
    public Rider(String name, String username, float lat, float lon, String password, String email, String address, int phone, String average_rating, int num_reviews, RiderStatusEnum status, Set<Ride> rides) {
        super(name, username, password, email, address, phone);
        this.lat = lat;
        this.lon = lon;
        this.average_rating = average_rating;
        this.num_reviews = num_reviews;
        this.status = RiderStatusEnum.AVAILABLE;
        this.rides = rides;
    }
}
