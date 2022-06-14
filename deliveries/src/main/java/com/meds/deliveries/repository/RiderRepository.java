package com.meds.deliveries.repository;

import java.util.List;
import java.util.Optional;

import com.meds.deliveries.enums.RiderStatusEnum;
import com.meds.deliveries.model.Rider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Integer> {
    Optional<Rider> findById(int id);
    Optional<Rider> findByEmail(String email);
    Optional<Rider> findByUsername(String username);
    List<Rider> findByStatus(RiderStatusEnum status);
}
