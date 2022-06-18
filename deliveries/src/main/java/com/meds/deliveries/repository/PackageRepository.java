package com.meds.deliveries.repository;

import java.util.List;

import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.model.Package;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {

    Package  findById(int id);
    List<Package> findByStatus(DeliveryStatusEnum status);
    

}
