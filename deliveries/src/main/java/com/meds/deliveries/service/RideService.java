package com.meds.deliveries.service;

import java.util.Date;
import java.util.List;

import com.meds.deliveries.model.Ride;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.repository.PackageRepository;
import com.meds.deliveries.repository.RideRepository;
import com.meds.deliveries.repository.RiderRepository;
import com.meds.deliveries.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideService {

    @Autowired
    RideRepository repository;

    @Autowired
    RiderRepository riderRepository;

    @Autowired
    PackageRepository packageRepository;

    

    public List<Ride> getAllRides() {
        return repository.findAll();
    }

    public List<Ride> getAllRidesFromRider(Rider r) {

        if (!riderRepository.existsById(r.getId())) {
            throw new ResourceNotFoundException("There are no rides for this rider, because he doesn't exist");
        }
   
        return r.getRides();
    }

    public Ride getRideById(int ride_id) {
        if (!repository.existsById(ride_id)){
            throw new ResourceNotFoundException(String.format("There is no ride with this id %s", ride_id));
        }
        return repository.findById(ride_id);
    }

    // algoritmo para escolher uma rider

    public Rider chooseRider(Package p) {
        p.setStatus(DeliveryStatusEnum.ACCEPTED);
        return riderRepository.findById(0);
    }

    public Ride rideInit(Package p) {

        Rider rider = new Rider();
        Ride ride = new Ride(p, rider);

        Date date = new Date();


        // ride.setRider(rider);
        p.setRide(ride);
        p.setStatus(DeliveryStatusEnum.ON_DELIVERY);

        ride.setTime_start(date);
        // ride.setRoute_start(rider.getRiderLocation());
        // ride.setRoute_end(p.getPackageLocation());
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
