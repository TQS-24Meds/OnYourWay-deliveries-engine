package com.meds.deliveries.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.verify;


import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.model.Admin;
import com.meds.deliveries.model.Coordinates;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.model.Store;
import com.meds.deliveries.repository.AdminRepository;
import com.meds.deliveries.repository.PackageRepository;
import com.meds.deliveries.repository.RiderRepository;
import com.meds.deliveries.repository.StoreRepository;

@ExtendWith(MockitoExtension.class)
public class PackageServiceTest {

    @InjectMocks
    private PackageService service;

    @Mock(lenient = true)
    private PackageRepository repository;
    private Package p;
    private Package p2;
    HashMap<Package, DeliveryStatusEnum> package_status = new HashMap<>();

    @Mock
    private AdminRepository adminRepository;
    private Admin admin;

    @Mock 
    private StoreRepository storeRepository;
    private Store store;
    

    @Mock
    private RiderRepository riderRepository;

    @Mock
    private SpringUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        // this.rider = new Rider("John Doe", "johndoe", "mypassword", "john@doe.com",
        // 912345678, "My house");
       
        
        this.admin = new Admin("Artur Romão", "arturomao", "12212", "artur@gmail.com", 96514778, Collections.emptyList());
        
        this.store = new Store("24 Meds", UUID.randomUUID(),  new Coordinates(87.2,87.1), admin);
        
        this.p = new Package(
                "Rua Dr. Mário Sacramento 12", "Joana Vedor", DeliveryStatusEnum.PENDENT, 1, store);
        this.p2= new Package("Rua das Cores", "Mariana Rosa", DeliveryStatusEnum.ON_DELIVERY, 2, store); 

        Mockito.when(repository.save(p)).thenReturn(p);
        Mockito.when(repository.findById(1)).thenReturn(p);
        package_status.put(p,p.getStatus());
        package_status.put(p2,p2.getStatus());



    }

    @AfterEach
    void cleanUp() {
        reset(repository);
    }

    @Test
    @DisplayName("Find package by ID")
    void testFindPackageById() {
        when(repository.findById(p.getId())).thenReturn(p);
        when(repository.existsById(p.getId())).thenReturn(true);
        System.out.println(p.getId());
        assertEquals(service.getPackageById(p.getId()), p);

        verify(repository, Mockito.times(1)).findById(Mockito.anyInt());

    }

    @Test
    @DisplayName("Find packages pendent")
    void testFindPackagesByStatus_whenStatusIsPendent() {
        List <Package> packages_res = new ArrayList<>();
        for (int i = 0; i < package_status.size(); i++) {
            if (package_status.get(package_status.keySet().toArray()[i]) == DeliveryStatusEnum.PENDENT){
                packages_res.add((Package) package_status.keySet().toArray()[i]);
            }
        } 
        when(repository.findByStatus(DeliveryStatusEnum.PENDENT)).thenReturn(packages_res);
    }

    @Test
    @DisplayName("Find packages pendent")
    void testFindPackagesByStatus_whenStatusIsDelivered() {
        List <Package> packages_res = new ArrayList<>();
        for (int i = 0; i < package_status.size(); i++) {
            if (package_status.get(package_status.keySet().toArray()[i]) == DeliveryStatusEnum.DELIVERED){
                packages_res.add((Package) package_status.keySet().toArray()[i]);
            }
        } 
        when(repository.findByStatus(DeliveryStatusEnum.DELIVERED)).thenReturn(packages_res);
    }


    @Test
    @DisplayName("Find packages accepted")
    void testFindPackagesByStatus_whenStatusIsAccepted() {
        List <Package> packages_res = new ArrayList<>();
        for (int i = 0; i < package_status.size(); i++) {
            if (package_status.get(package_status.keySet().toArray()[i]) == DeliveryStatusEnum.ACCEPTED){
                packages_res.add((Package) package_status.keySet().toArray()[i]);
            }
        } 
        when(repository.findByStatus(DeliveryStatusEnum.ACCEPTED)).thenReturn(packages_res);
    }


    @Test
    @DisplayName("Find packages on delivery")
    void testFindPackagesByStatus_whenStatusIsOnDelivery() {
        List <Package> packages_res = new ArrayList<>();
        for (int i = 0; i < package_status.size(); i++) {
            if (package_status.get(package_status.keySet().toArray()[i]) == DeliveryStatusEnum.ON_DELIVERY){
                packages_res.add((Package) package_status.keySet().toArray()[i]);
            }
        } 
        when(repository.findByStatus(DeliveryStatusEnum.ON_DELIVERY)).thenReturn(packages_res);
    }

    @Test
    @DisplayName("Find packages on delivery")
    void testFindPackagesByStatus_whenStatusIsPickedUp() {
        List <Package> packages_res = new ArrayList<>();
        for (int i = 0; i < package_status.size(); i++) {
            if (package_status.get(package_status.keySet().toArray()[i]) == DeliveryStatusEnum.PICKED_UP){
                packages_res.add((Package) package_status.keySet().toArray()[i]);
            }
        } 
        when(repository.findByStatus(DeliveryStatusEnum.PICKED_UP)).thenReturn(packages_res);

    }

    @Test
    @DisplayName("Get package status")
    void testGetPackagesStatus() {  
        Mockito.when(repository.findById(1)).thenReturn(p);
        when(repository.existsById(p.getId())).thenReturn(true);

        System.out.println(p);
        System.out.println(p.getId());

        assertEquals(service.getPackageStatus(p), p.getStatus());
    }




    // Test
    // void whenPackageAccepted_UpdateStatus

    // package has to have a valid rider accepting it
    // @Test
    // @DisplayName("")
    // void testAssociateRiderToPackage_whenInvalidRider() 

        //Mockito.when(riderRepository.findByUsername("username_123")).thenReturn(Optional.empty());

    
    // package has to be delivered by a rider without a package atready

    // package update with valid rider

    // package update with error connection

    // package picked verify delivery time

}
