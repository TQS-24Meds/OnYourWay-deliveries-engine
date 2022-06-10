package com.meds.deliveries.service;

import com.meds.deliveries.exception.DuplicatedObjectException;
import com.meds.deliveries.exception.InvalidLoginException;
import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.model.Ride;

import com.meds.deliveries.repository.PackageRepository;
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
public class PackageService {

    @Autowired 
    PackageRepository repository;
  
    List<Package> getAllPackages() { 
        return repository.findAll(); 
    }

    //find all packages on delivery

    //find all packages 

    //get a status

    //set package as delivered

    

  




    
}
