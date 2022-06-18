package com.meds.deliveries.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "route_start_lat")
    private Double routeStartLat;

    @Column(name = "route_start_long")
    private Double routeStartLong;

    
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

    public Ride(Package ride_package) {
        this.ride_package = ride_package;
        //this.route_start = new Coordinates
        //this.route_end = ride_package.getPackageLocation();


    }

}
