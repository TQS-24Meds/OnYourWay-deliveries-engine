package com.meds.deliveries.service;

import com.meds.deliveries.dto.UserDTO;
import com.meds.deliveries.enums.RiderStatusEnum;
import com.meds.deliveries.exception.ExistentUserException;
import com.meds.deliveries.exception.InvalidLoginException;
import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Coordinates;
import com.meds.deliveries.model.Ride;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.repository.RiderRepository;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RiderService {

    @Autowired RiderRepository repository;
    @Autowired PasswordEncoder passwordEncoder;
    
    public List<Rider> getAllRiders() { return repository.findAll(); }

    public Rider getRiderById(int rider_id) {
        return repository.findById(rider_id);
    }
    public List<Rider> findKeyword(String keyword)  {

        return repository.findKeyword(keyword);
    }


    public List<Ride> getAllRidesByRiderId(int rider_id){ 
        if (!repository.existsById(rider_id)) {
            throw new ResourceNotFoundException("There is no rider with this id:" + rider_id);
        }

        return repository.findById(rider_id).getRides();
    }
    
    public Rider registerRider(UserDTO userDTO) {
        Rider rider = new Rider(userDTO.getName(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getPhone(), "deliveries", userDTO.getAddress());
        if (repository.existsByUsername(rider.getUsername()))
            throw new ExistentUserException("The provided username is already taken.");
        return repository.save(rider);
    }

    public Rider updateLocation(Coordinates riderCoord, Rider rider) throws ResourceNotFoundException {
        
        rider.setRiderLocation(riderCoord);
        repository.save(rider);

        if(rider.getRiderLocation() == riderCoord){

            log.info("RIDER SERVICE: Rider location updated successfully");
            return rider;

        } else {

            log.error("RIDER SERVICE: Invalid rider email when updating location");
            throw new ResourceNotFoundException("Failed to update Rider location");
        }
    }

    public List<Rider> getRidersByStatus(RiderStatusEnum status, List<Rider> riders) throws InvalidLoginException {
        
        List<Rider> ridersWithStatus = new ArrayList<>();
    

        System.out.println("all riders service :" + riders );
        for (Rider rider : riders) {
            if (rider.getStatus() == status){ ridersWithStatus.add(rider);}
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

    public Rider updateRiderStatus(Rider rider) {
        RiderStatusEnum status = rider.getStatus();

        switch (status){
            case UNAVAILABLE:
                rider.setStatus(RiderStatusEnum.AVAILABLE);
                break;
            case AVAILABLE:
                rider.setStatus(RiderStatusEnum.UNAVAILABLE);

        }
        return rider;

    }



}
