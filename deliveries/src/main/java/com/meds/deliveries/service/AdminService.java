package com.meds.deliveries.service;
import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.*;
import com.meds.deliveries.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service

public class AdminService {
    @Autowired AdminRepository repository;
    public List<Admin> getAllAdmins() { return repository.findAll(); }

   

    public List<Admin> getAdmins() {
        return repository.findAll();
    }

    public Optional<Admin> getAdminById(int id) {
        return repository.findById(id);
    }

    public Admin saveAdmin(Admin admin){
        return repository.save(admin);
    }

    public Admin updateAdmin(Admin admin) throws ResourceNotFoundException {
        
        int id = admin.getId();

        Admin existingAdmin = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id:" + id));

        existingAdmin.setName(admin.getName());
        existingAdmin.setStores(admin.getStores());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setPhone(admin.getPhone());
        existingAdmin.setPassword(admin.getPassword());
        existingAdmin.setStores(admin.getStores());
        return repository.save(existingAdmin);
    }


}
