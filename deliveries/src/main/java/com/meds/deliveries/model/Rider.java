package com.meds.deliveries.model;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meds.deliveries.enums.RiderStatusEnum;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "rider")
public class Rider {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_rider")
    private int id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person_rider", referencedColumnName = "id_person")
    @JsonIgnore
    private Person rider;

    @Column(name = "lat", nullable = false)
    private float lat;

    @Column(name = "lon", nullable = false)
    private float lon;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "average_rating", nullable = false)
    private float average_rating;

    @Column(name = "num_reviews", nullable = false)
    private int num_reviews;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RiderStatusEnum status;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "rider")
    @JsonIgnore
    private Set<Ride> rides;

    // Unavailable
    public Rider(Person rider, String address, float average_rating, int num_reviews, RiderStatusEnum status, Set<Ride> rides) {
        this.rider = rider;
        this.address = address;
        this.average_rating = average_rating;
        this.num_reviews = num_reviews;
        this.status = RiderStatusEnum.UNAVAILABLE;
        this.rides = rides;
    }

    // Available
    public Rider(Person rider, float lat, float lon, String address, float average_rating, int num_reviews, RiderStatusEnum status, Set<Ride> rides) {
        this.rider = rider;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.average_rating = average_rating;
        this.num_reviews = num_reviews;
        this.status = RiderStatusEnum.AVAILABLE;
        this.rides = rides;
    }
}
