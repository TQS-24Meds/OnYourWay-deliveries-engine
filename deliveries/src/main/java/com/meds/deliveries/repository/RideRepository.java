package com.meds.deliveries.repository;

import com.meds.deliveries.model.Ride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer>{
    Ride findById(int id);
}
