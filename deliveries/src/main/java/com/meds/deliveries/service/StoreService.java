package com.meds.deliveries.service;

import java.util.List;

import com.meds.deliveries.model.Store;
import com.meds.deliveries.repository.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    
    @Autowired StoreRepository repository;

    public List<Store> getAllStores() {return repository.findAll();}

    public Store getStoreById(int store_id) {
        return repository.findById(store_id).get();
    }

}
