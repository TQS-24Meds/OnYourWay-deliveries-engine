package com.meds.deliveries.model;

import java.sql.Timestamp;

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
    private PackageOrder ride_package;

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

    public Ride(Rider rider, PackageOrder ride_package, HashMap<Float,Float> route_start, HashMap<Float,Float> route_end, Timestamp time_start, Timestamp time_end, int rating) {
        this.rider = rider;
        this.ride_package = ride_package;
        this.route_start = route_start;
        this.route_end = route_end;
        this.time_start = time_start;
        this.time_end = time_end;
        this.rating = rating;
    }

    public int getId() { return this.id; }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", rider='" + getRider() + "'" +
            ", ride_package='" + getRide_package() + "'" +
            ", route_start='" + getRoute_start() + "'" +
            ", route_end='" + getRoute_end() + "'" +
            ", time_start='" + getTime_start() + "'" +
            ", time_end='" + getTime_end() + "'" +
            ", rating='" + getRating() + "'" +
            "}";
    }


}
