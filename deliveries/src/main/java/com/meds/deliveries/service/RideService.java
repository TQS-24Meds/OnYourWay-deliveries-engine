package com.meds.deliveries.service;

import java.util.List;

import com.meds.deliveries.model.Ride;
import com.meds.deliveries.repository.RideRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideService {
   
    @Autowired RideRepository repository;

    public List<Ride> getAllRides() {
        return repository.findAll();
    }

    public Ride getRideById(int ride_id) {
        return repository.getById(ride_id);
    }
}
