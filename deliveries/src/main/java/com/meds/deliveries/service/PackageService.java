package com.meds.deliveries.service;

import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.exception.DuplicatedObjectException;
import com.meds.deliveries.exception.InvalidLoginException;
import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.model.Ride;

import com.meds.deliveries.repository.PackageRepository;
import com.meds.deliveries.repository.RiderRepository;

import lombok.extern.log4j.Log4j2;
import net.bytebuddy.implementation.StubMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class PackageService {

    @Autowired 
    PackageRepository repository;
  
    //find all packages 

    List<Package> getAllPackages() { 
        return repository.findAll(); 
    }
    
    //find all packages on delivery
    List<Package> getPackagesOnDelivery() throws ResourceNotFoundException{
        
        List<Package> onDelivery = repository.findByStatus(DeliveryStatusEnum.ON_DELIVERY).orElseThrow(() -> new ResourceNotFoundException("There are no packages on delivery"));
        
        return onDelivery;
    }

    //find all packages pendent
    List<Package> getPackagesPendent() throws ResourceNotFoundException{
    
        List<Package> pendent = repository.findByStatus(DeliveryStatusEnum.PENDENT).orElseThrow(() -> new ResourceNotFoundException("There are no packages on delivery"));
        
        return pendent;
    }

    //find all packages accepted
    List<Package> getPackagesAccepted() throws ResourceNotFoundException{

        List<Package> packages_accepted = repository.findByStatus(DeliveryStatusEnum.ACCEPTED).orElseThrow(() -> new ResourceNotFoundException("There are no packages on delivery"));
        
        return packages_accepted;
    }


    //find all packages delivered
    List<Package> getPackagesPickedUp() throws ResourceNotFoundException{

        List<Package> packages_delivered = repository.findByStatus(DeliveryStatusEnum.DELIVERED).orElseThrow(() -> new ResourceNotFoundException("There are no packages on delivery"));
        
        return packages_delivered;
    }
    

    public Package getPackageById(int id) throws ResourceNotFoundException {
        
        Package  p = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There are no packages with this id"));
        
        return p;
    }

    //get status of a package 
    public DeliveryStatusEnum getPackageStatus(int package_id) throws ResourceNotFoundException{
        Package  p = repository.findById(package_id).orElseThrow(() -> new ResourceNotFoundException("There are no packages with this id"));
        DeliveryStatusEnum status = p.getStatus();
        return status;

    }

    //set package as delivered

    public Package updatePackageStatus(int id, DeliveryStatusEnum status) throws ResourceNotFoundException {
        
        Package  p = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There are no packages with this id"));
        p.setStatus(status);
        return p;

    }

    

  




    
}
