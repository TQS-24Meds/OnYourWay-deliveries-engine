package com.meds.deliveries.model;

import java.sql.Timestamp;

import java.util.HashMap;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "ride")
public class Ride {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_ride")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_rider", nullable = false)
    private Rider rider;

    @OneToOne(mappedBy = "package", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Ride ride;

    //* HashMap<Latitude, Longitude>
    @Column(name = "route_start", nullable = false)
    private HashMap<Float, Float> route_start;

    @Column(name = "route_end", nullable = false)
    private HashMap<Float, Float> route_end;

    @CreationTimestamp
    @Column(name = "time_start")
    private Timestamp time_start;

    @UpdateTimestamp
    @Column(name = "time_end")
    private Timestamp time_end;

    @Column(name = "rating")
    @Min(value = 0, message = "Review should not be less than 0.")
    @Max(value = 5, message = "Review should not be more than 5.")
    private int rating;

    public Ride() {}

    public Ride(int id, Rider rider, Ride ride, HashMap<Float,Float> route_start, HashMap<Float,Float> route_end, Timestamp time_start, Timestamp time_end, int rating) {
        this.id = id;
        this.rider = rider;
        this.ride = ride;
        this.route_start = route_start;
        this.route_end = route_end;
        this.time_start = time_start;
        this.time_end = time_end;
        this.rating = rating;
    }

    public int getId() { return this.id; }

    public Rider getRider() { return this.rider; }

    public void setRider(Rider rider) { this.rider = rider; }

    public Ride getRide() { return this.ride; }

    public void setRide(Ride ride) { this.ride = ride; }

    public HashMap<Float,Float> getRoute_start() { return this.route_start; }

    public void setRoute_start(HashMap<Float,Float> route_start) { this.route_start = route_start; }

    public HashMap<Float,Float> getRoute_end() { return this.route_end; }

    public void setRoute_end(HashMap<Float,Float> route_end) { this.route_end = route_end; }

    public Timestamp getTime_start() { return this.time_start; }

    public void setTime_start(Timestamp time_start) { this.time_start = time_start; }

    public Timestamp getTime_end() { return this.time_end; }

    public void setTime_end(Timestamp time_end) { this.time_end = time_end; }

    public int getRating() { return this.rating; }

    public void setRating(int rating) { this.rating = rating; }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", rider='" + getRider() + "'" +
            ", ride='" + getRide() + "'" +
            ", route_start='" + getRoute_start() + "'" +
            ", route_end='" + getRoute_end() + "'" +
            ", time_start='" + getTime_start() + "'" +
            ", time_end='" + getTime_end() + "'" +
            ", rating='" + getRating() + "'" +
            "}";
    }


}
