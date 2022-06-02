package com.meds.deliveries.repository;

import java.util.List;

import com.meds.deliveries.enums.RiderStatusEnum;
import com.meds.deliveries.model.Rider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository  extends JpaRepository<Rider, Integer> {
    Rider findById(int id);
    List<Rider> findByStatus(RiderStatusEnum status);
    List<Rider> findAll();
}
