package com.meds.deliveries.service;

import com.meds.deliveries.enums.RiderStatusEnum;
import com.meds.deliveries.exception.DuplicatedObjectException;
import com.meds.deliveries.exception.InvalidLoginException;
import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Ride;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.repository.RiderRepository;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RiderService {

    @Autowired
    RiderRepository repository;

    List<Rider> getAllRiders() {
        return repository.findAll();
    }

    List<Ride> getAllRides(Rider rider) {
        return repository.findById(rider.getId()).getRides();
    }

    Rider registerRider(Rider rider) throws DuplicatedObjectException {
        if (repository.findByEmail(rider.getEmail()).isEmpty()) {
            rider.setPassword(rider.getPassword());
            repository.saveAndFlush(rider);

            log.info("RIDER SERVICE: Rider saved successfully");
            return rider;
        }

        log.error("RIDER SERVICE: Duplicated rider email, when saving rider");
        throw new DuplicatedObjectException("Rider with this email already exists.");
    }

    Rider updateLocation(float lat, float lon, Rider rider) throws ResourceNotFoundException {

        rider.setLat(lat);
        rider.setLon(lon);
        repository.save(rider);

        if (rider.getLat() == lat && rider.getLon() == lon) {

            log.info("RIDER SERVICE: Rider location updated successfully");
            return rider;

        } else {

            log.error("RIDER SERVICE: Invalid rider email when updating location");
            throw new ResourceNotFoundException("Failed to update Rider location");
        }
    }

    public List<Rider> getRidersByStatus(RiderStatusEnum status, List<Rider> riders) throws InvalidLoginException {

        List<Rider> ridersWithStatus = new ArrayList<>();

        System.out.println("all riders service :" + riders);
        for (Rider rider : riders) {
            if (rider.getStatus() == status) {
                ridersWithStatus.add(rider);
            }
        }

        log.info("RIDER SERVICE: Retrieved riders with specific status with success");
        return ridersWithStatus;
    }

    public Map<String, Object> getRatingStatistics(Rider rider) throws InvalidLoginException {

        Map<String, Object> resp = new TreeMap<>();
        resp.put("numReviews", rider.getNum_reviews());
        resp.put("avgReviews", rider.getAverage_rating());

        log.info("RIDER SERVICE: Retrieved rating statistics with success");
        return resp;
    }

    // update Rider Status

    public Rider updateRiderStatus(Rider rider) {

        // unavailable
        if (rider.getStatus() == RiderStatusEnum.AVAILABLE) {
            rider.setStatus(RiderStatusEnum.UNAVAILABLE);
            log.info("RIDER SERVICE: Rider Status set to unavailable");

        }

        // available
        else if (rider.getStatus() == RiderStatusEnum.UNAVAILABLE) {
            rider.setStatus(RiderStatusEnum.AVAILABLE);
            log.info("RIDER SERVICE: Rider Status set to available");
        }

        return rider;

    }

    //Get Rider's location

}
