package com.meds.deliveries.service;

import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Package;

import com.meds.deliveries.repository.PackageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PackageService {

    @Autowired 
    PackageRepository repository;
  
    //find all packages 

  
    public List<Package> getAllPackages() { 
        return repository.findAll(); 
    }
    
    //find all packages on delivery
    public List<Package> getPackagesOnDelivery() {
        
        List<Package> onDelivery = repository.findByStatus(DeliveryStatusEnum.ON_DELIVERY);
        return onDelivery;
    }

    //find all packages by status
    public List<Package> getPackagesByStatus(DeliveryStatusEnum status){
    
        List<Package> res = repository.findByStatus(status);
        
        return res;
    }

    //find all packages accepted
    public List<Package> getPackagesAccepted() throws ResourceNotFoundException{

        List<Package> packages_accepted = repository.findByStatus(DeliveryStatusEnum.ACCEPTED);
        
        return packages_accepted;
    }


    //find all packages delivered
    public List<Package> getPackagesPickedUp() throws ResourceNotFoundException{

        List<Package> packages_delivered = repository.findByStatus(DeliveryStatusEnum.DELIVERED);
        
        return packages_delivered;
    }
    

    public Package getPackageById(int id) throws ResourceNotFoundException {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("There are no packages with this ID:" + id);
        }
        return repository.findById(id);
     
    }

    //get status of a package 
    public DeliveryStatusEnum getPackageStatus(Package p) throws ResourceNotFoundException{
        if (!repository.existsById(p.getId())) {
            throw new ResourceNotFoundException("There are no packages with this ID:" + p.getId());
        }

        DeliveryStatusEnum status = p.getStatus();
        return status;

    }

    //set package as delivered

    public Package updatePackageStatus(int id, DeliveryStatusEnum status) throws ResourceNotFoundException {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("There are no packages with this ID:" + id);
        }
        Package  p = repository.findById(id);
        p.setStatus(status);
        return p;

    }

    

  




    
}
