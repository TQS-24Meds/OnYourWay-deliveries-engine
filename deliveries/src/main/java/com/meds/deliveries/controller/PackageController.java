package com.meds.deliveries.controller;

import java.util.ArrayList;
import java.util.List;

import com.meds.deliveries.service.PackageService;
import com.meds.deliveries.model.Package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PackageController {
    
    private final PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/packages")
    public List<Package> getAllPackages() {
        return packageService.getAllPackages();
    }

    @GetMapping("/package/{package_id}")
    public Package getPackageById(@PathVariable(value = "package_id") int package_id) {
        return packageService.getPackageById(package_id);
    }

    @GetMapping("/packages/store/{store_id}")
    public List<Package> getPackagesByStoreId(@PathVariable(value = "store_id") int store_id) {
        List<Package> packages = getAllPackages();
        List<Package> store_packages = new ArrayList<Package>();
        for (Package p : packages) {
            if (p.getStore_id() == store_id) {
                store_packages.add(p);
            }
        }
        return store_packages;
    }
}