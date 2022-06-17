package com.meds.deliveries.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.meds.deliveries.model.Ride;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.model.Package;

import com.meds.deliveries.repository.RideRepository;
import com.meds.deliveries.repository.RiderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideService {

    @Autowired
    RideRepository repository;

    @Autowired
    RiderRepository rider_repository;

    public List<Ride> getAllRides() {
        return repository.findAll();
    }

    public Ride getRideById(int ride_id) {
        return repository.getById(ride_id);
    }

    // algoritmo para escolher uma rider

    public Rider chooseRider(Package p) {
        p.setStatus(DeliveryStatusEnum.ACCEPTED);
        return rider_repository.getById(0);
    }

    public Ride createRide(Package p) {

        Ride ride = new Ride(p);
        Rider rider = new Rider();

        Date date = new Date();
        Integer rider_id = p.getRider_id();

        HashMap<Double, Double> client_addr = new HashMap<>();
        HashMap<Double, Double> rider_addr = new HashMap<>();

        client_addr.put(p.getClient_lat(), p.getClient_long());
        rider_addr.put(rider.getLat(), rider.getLon());

        // ride.setRider(rider);
        p.setRide(ride);
        p.setStatus(DeliveryStatusEnum.ON_DELIVERY);

        ride.setTime_start(date);
        ride.setRoute_start(rider_addr);
        ride.setRoute_end(client_addr);
        repository.save(ride);

        return ride;

    }

    public void finishRide(Package p) {
        Date date = new Date();
        p.setStatus(DeliveryStatusEnum.DELIVERED);
        Ride r = p.getRide();
        r.setTime_end(date);
    }



}
