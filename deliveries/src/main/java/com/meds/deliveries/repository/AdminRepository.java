package com.meds.deliveries.repository;

import com.meds.deliveries.model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
    Admin findById(int id);
}
