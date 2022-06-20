package com.meds.deliveries.service;

import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.exception.InvalidLoginException;
import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.model.Store;
import com.meds.deliveries.repository.PackageRepository;
import com.meds.deliveries.repository.RiderRepository;
import com.meds.deliveries.repository.StoreRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageService {

    @Autowired
    PackageRepository repository;

    @Autowired
    RiderRepository riderRepository;
    @Autowired
    StoreRepository storeRP;
    @Autowired
    SpringUserDetailsService SpringUserDetailsService;

    // find all packages
    public List<Package> getAllPackages() {
        return repository.findAll();
    }

    // find all packages by status
    public List<Package> getPackagesByStatus(DeliveryStatusEnum status) {
        List<Package> res = repository.findByStatus(status);
        if (res.isEmpty()){
            throw new ResourceNotFoundException("There are no packages with this status:" + status);
        }
        return res;
    }

    // find all packages pendent
    public List<Package> getPackagesPendent() throws ResourceNotFoundException {
        List<Package> packages_pendent = repository.findByStatus(DeliveryStatusEnum.PENDENT);
        return packages_pendent;
    }

    // find all packages accepted
    public List<Package> getPackagesAccepted() throws ResourceNotFoundException {
        List<Package> packages_accepted = repository.findByStatus(DeliveryStatusEnum.ACCEPTED);
        return packages_accepted;
    }

    // find all packages on delivery
    public List<Package> getPackagesOnDelivery() {
        List<Package> onDelivery = repository.findByStatus(DeliveryStatusEnum.ON_DELIVERY);
        return onDelivery;
    }

    // find all packages picked up
    public List<Package> getPackagesPickedUp() throws ResourceNotFoundException {
        List<Package> packages_picked_up = repository.findByStatus(DeliveryStatusEnum.PICKED_UP);
        return packages_picked_up;
    }

    // find all packages delivered
    public List<Package> getPackagesDelivered() throws ResourceNotFoundException {
        List<Package> packages_delivered = repository.findByStatus(DeliveryStatusEnum.DELIVERED);
        return packages_delivered;
    }


    // find package by id
    public Package getPackageById(int id) throws ResourceNotFoundException {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("There are no packages with this ID:" + id);
        }
        return repository.findById(id).get();

    }

    // get status of a package
    public DeliveryStatusEnum getPackageStatus(Package p) throws ResourceNotFoundException {
        if (!repository.existsById(p.getId())) {
            throw new ResourceNotFoundException("There are no packages with this ID:" + p.getId());
        }
        DeliveryStatusEnum status = p.getStatus();
        return status;
    }

    // updating package status
    public void updatePackageStatus(Package p) throws ResourceNotFoundException {

        if (!repository.existsById(p.getId())) {
            throw new ResourceNotFoundException("There are no packages with this ID:" + p.getId());
        }
       

        DeliveryStatusEnum current_status = p.getStatus();
        DeliveryStatusEnum next_status = DeliveryStatusEnum.getNext(current_status);
        p.setStatus(next_status);

    }

    // Rider accepting a package - not tested
    public Package acepptingPackage(Package p, Rider r) {
        if (!repository.existsById(p.getId()) && p.getStatus() != DeliveryStatusEnum.PENDENT) {
            throw new ResourceNotFoundException("There are no packages pendent with this ID:" + p.getId());
        }

        if(!riderRepository.existsById(r.getId()) ) {
            throw new ResourceNotFoundException("There are no riders with this ID:" + r.getId());
        }

        this.updatePackageStatus(p);

        return p;
    }

     public Package receiveNewOrder(String storeToken, Map<String, Object> data) throws  InvalidLoginException {
        
        String client_addr = Optional.ofNullable(data.get("address")).toString() ;
        String client_name = Optional.ofNullable(data.get("name")).toString() ;
        String order_id = Optional.ofNullable(data.get("order_id"))).toString();
        Store store = storeRP.findById(Integer.parseInt(Optional.ofNullable(data.get("storeid")).orElseThrow(()-> new ResourceNotFoundException("Store not found"))));

        Package pac = new Package(client_addr, client_name, order_id, store);
        repository.save(pac);

        log.info("Store successfully retrieved newest order");
        return pac;
    }
 

}
