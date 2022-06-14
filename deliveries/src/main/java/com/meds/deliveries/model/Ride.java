package com.meds.deliveries.model;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ride")
public class Ride {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_ride")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_rider", nullable = false)
    private Rider rider;

    @OneToOne(mappedBy = "ride", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Package ride_package;

    //* HashMap<Latitude, Longitude>
    @Column(name = "route_start", nullable = false)
    private HashMap<Float, Float> route_start;

    @Column(name = "route_end", nullable = false)
    private HashMap<Float, Float> route_end;

    @CreationTimestamp
    @Column(name = "time_start")
    private Date time_start;

    @UpdateTimestamp
    @Column(name = "time_end")
    private Date time_end;

    @Column(name = "rating")
    @Min(value = 0, message = "Review should not be less than 0.")
    @Max(value = 5, message = "Review should not be more than 5.")
    private int rating;

    public Ride(Package ride_package, int rating) {
        this.ride_package = ride_package;
        this.route_start = new HashMap<Float, Float>();
        this.route_end = new HashMap<Float, Float>();
        this.time_start = new Date();
        this.time_end = new Date();
        this.rating = rating;
    }

}
