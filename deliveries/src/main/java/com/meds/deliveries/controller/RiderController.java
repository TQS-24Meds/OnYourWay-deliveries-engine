package com.meds.deliveries.controller;

import java.util.ArrayList;
import java.util.List;

import com.meds.deliveries.model.*;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.service.RiderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RiderController {
    
    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping("/riders")
    public List<Rider> getAllRiders() {
        return riderService.getAllRiders();
    }

    @GetMapping("/rider/{rider_id}")
    public Rider getRiderDetails(@PathVariable(value = "rider_id") int rider_id) {
        return riderService.getRiderById(rider_id);
    }

    @GetMapping("/rider/{rider_id}/package/{package_id}")
    public Package getRiderSpecificPackage(@PathVariable(value = "rider_id") int rider_id, @PathVariable(value = "package_id") int package_id) {
        List<Ride> rides = riderService.getAllRidesByRiderId(rider_id);
        Package rider_package = null;
        for (Ride r : rides) {
            if (r.getRide_package().getId() == package_id) {
                rider_package = r.getRide_package();
            }
        }
        return rider_package;
    }

    @GetMapping("/rider/{rider_id}/packages")
    public List<Package> getRiderListOfPackages(@PathVariable(value = "rider_id") int rider_id) {
        List<Ride> rides = riderService.getAllRidesByRiderId(rider_id);
        List<Package> packages = new ArrayList<Package>();
        for (Ride r : rides) {
            packages.add(r.getRide_package());
        }
        return packages;
    }
    
    @GetMapping("/rider/{rider_id}/ride/{ride_id}")
    public Ride getSpecificRideByRiderId(@PathVariable(value = "rider_id") int rider_id, @PathVariable(value = "ride_id") int ride_id) {
        List<Ride> rides = riderService.getAllRidesByRiderId(rider_id);
        Ride ride = null;
        for (Ride r : rides) {
            if (r.getId() == ride_id) {
                ride = r;
            }
        }
        return ride;
    }

    @GetMapping("/rider/{rider_id}/rides")
    public List<Ride> getRiderListOfRides(@PathVariable(value = "rider_id") int rider_id) {
        return riderService.getAllRidesByRiderId(rider_id);
    }
}
