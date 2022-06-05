package com.meds.deliveries.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meds.deliveries.enums.RiderStatusEnum;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "rider")
public class Rider extends Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_rider")
    private int id;

    // @NotNull
    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_person_rider", referencedColumnName = "id_person")
    // @JsonIgnore
    // private Person rider;

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
    private List<Ride> rides;

    @Builder
    // Unavailable
    public Rider(String name, String username, String password, String email, int phone, String address) {
        super(name, username, password, email, phone);
        this.address = address;
        this.average_rating = 0.0f;
        this.num_reviews = 0;
        this.status = RiderStatusEnum.UNAVAILABLE;
        this.rides = new ArrayList<>();
    }
    
    @Builder
    // Available
    public Rider(String name, String username, String password, String email, int phone, String address, float lat, float lon) {
        super(name, username, password, email, phone);
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.average_rating = 0.0f;
        this.num_reviews = 0;
        this.status = RiderStatusEnum.AVAILABLE;
        this.rides = new ArrayList<>();
    }
}
