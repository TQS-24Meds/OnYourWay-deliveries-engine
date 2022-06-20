package com.meds.deliveries.service;

import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.exception.InvalidLoginException;
import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.model.Store;
import com.meds.deliveries.repository.PackageRepository;
import com.meds.deliveries.repository.RiderRepository;

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

    //public Package receiveNewOrder(String storeToken, Map<String, Object> data) throws  InvalidLoginException {
/*         Store store = SpringUserDetailsService.getStoreFromToken(storeToken);
        if (store == null) {
            log.error("PURCHASE SERVICE: Invalid store token, when store tried to get new order");
            throw new InvalidLoginException("There is no Store associated with this token");
        } */

        /* String error = "invalid data";

        Object personName = Optional.ofNullable(data.get("personName")) .orElseThrow(() -> {
            log.error("PURCHASE SERVICE: Invalid data, personName, when store tried to get new order");
            return new InvalidValueException(error);
        });

        if (!(personName instanceof String)) {
            log.error("PURCHASE SERVICE: Invalid data when store tried to get new order -> person name is not of type String");
            throw new InvalidValueException(error);
        }

        Object address = Optional.ofNullable(data.get("address")).orElseThrow(() -> {
            log.error("PURCHASE SERVICE: Invalid data, address, when store tried to get new order");
            return new InvalidValueException(error);
        });

        Address addr = new Address();
        try {
            addr.setAddress(((Map<String, String>) address).get("address"));
            addr.setCity(((Map<String, String>) address).get("city"));
            addr.setCountry(((Map<String, String>) address).get("country"));
            addr.setPostalCode(((Map<String, String>) address).get("postalCode"));
        } catch (Exception ex) {
            log.error("PURCHASE SERVICE: Invalid data, address, when store tried to get new order");
            throw new InvalidValueException(error);
        }

        addressRepository.save(addr);
        Purchase purchase = new Purchase(addr, store, (String) personName);
        purchaseRepository.save(purchase);

        log.info("PURCHASE SERVICE: Store successfully retrieved newest order");
        return purchase;
    } */


}
