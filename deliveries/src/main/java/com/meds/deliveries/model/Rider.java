package com.meds.deliveries.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meds.deliveries.enums.RiderStatusEnum;


@Entity
@Table(name = "rider")
public class Rider extends Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_rider")
    private int id;

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

    public Rider() {}

    // Unavailable
    public Rider(int id, String name, String username, String password, String email, String address, int phone, String average_rating, int num_reviews, RiderStatusEnum status, Set<Ride> rides) {
        super(name, username, password, email, address, phone);
        this.id = id;
        this.average_rating = average_rating;
        this.num_reviews = num_reviews;
        this.status = RiderStatusEnum.UNAVAILABLE;
        this.rides = rides;
    }

    // Available
    public Rider(int id, String name, String username, float lat, float lon, String password, String email, String address, int phone, String average_rating, int num_reviews, RiderStatusEnum status, Set<Ride> rides) {
        super(name, username, password, email, address, phone);
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.average_rating = average_rating;
        this.num_reviews = num_reviews;
        this.status = RiderStatusEnum.AVAILABLE;
        this.rides = rides;
    }

    public int getId() { return this.id; }

    public float getLat() { return this.lat; }

    public void setLat(float lat) { this.lat = lat; }

    public float getLon() { return this.lon; }

    public void setLon(float lon) { this.lon = lon; }

    public String getAverage_rating() { return this.average_rating; }

    public void setAverage_rating(String average_rating) { this.average_rating = average_rating; }

    public int getNum_reviews() { return this.num_reviews; }

    public void setNum_reviews(int num_reviews) { this.num_reviews = num_reviews; }

    public RiderStatusEnum getStatus() { return this.status; }

    public void setStatus(RiderStatusEnum status) { this.status = status; }

    public Set<Ride> getRides() { return this.rides; }

    public void setRides(Set<Ride> rides) { this.rides = rides; }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", username='" + getUsername() + "'" +
            ", lat='" + getLat() + "'" +
            ", lon='" + getLon() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", average_rating='" + getAverage_rating() + "'" +
            ", num_reviews='" + getNum_reviews() + "'" +
            ", status='" + getStatus() + "'" +
            ", rides='" + getRides() + "'" +
            "}";
    }
}
