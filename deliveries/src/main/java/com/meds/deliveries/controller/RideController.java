package com.meds.deliveries.controller;

import java.util.List;

import com.meds.deliveries.model.*;
import com.meds.deliveries.service.RideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RideController {
    
    private final RideService rideService;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/rides")
    public List<Ride> getAllRides() {
        return rideService.getAllRides();
    }

    @GetMapping("/ride/{ride_id}")
    public Ride getSpecificRideById(@PathVariable(value = "ride_id") int ride_id) {
        return rideService.getRideById(ride_id);
    }
}
