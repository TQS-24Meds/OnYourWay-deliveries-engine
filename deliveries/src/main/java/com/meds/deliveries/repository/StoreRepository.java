package com.meds.deliveries.repository;


import com.meds.deliveries.model.Store;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer>{
    Store findById(int id);

}
