package com.meds.deliveries.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Coordinates;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.model.Store;
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

    @Mock(lenient = true)
    private StoreRepository storeRepository;
    private Store store;

    @Mock(lenient = true)
    private RiderRepository riderRepository;
    private Rider rider;

    @Mock
    private SpringUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {

        this.rider = new Rider("John Doe", "johndoe", "mypassword", "john@doe.com", 912345678, "deliveries", "My house");
        Mockito.when(riderRepository.save(rider)).thenReturn(rider);

        this.store = new Store("24 Meds", UUID.randomUUID(), new Coordinates(87.2, 87.1));
        Mockito.when(storeRepository.save(store)).thenReturn(store);

        this.p = new Package(
                "Rua Dr. Mário Sacramento 12", "Joana Vedor", 1, store);
        this.p2 = new Package("Rua das Cores", "Mariana Rosa", 2, store);
        this.p2.setStatus(DeliveryStatusEnum.ON_DELIVERY);

        Mockito.when(repository.save(p)).thenReturn(p);
        Mockito.when(repository.save(p2)).thenReturn(p2);

        Mockito.when(repository.findById(1)).thenReturn(Optional.of(p));
        package_status.put(p, p.getStatus());
        package_status.put(p2, p2.getStatus());

    }

    @AfterEach
    void cleanUp() {
        reset(repository);
    }

    @Test
    @DisplayName("Find package by ID")
    void whenSearchingForPackageId_returnRightPackage() {
        when(repository.findById(p.getId())).thenReturn(Optional.of(p));
        when(repository.existsById(p.getId())).thenReturn(true);
        System.out.println(p.getId());

        assertEquals(p, service.getPackageById(p.getId()));
        Assertions.assertNotNull(p);
        verify(repository, Mockito.times(1)).findById(Mockito.anyInt());

    }

    @Test
    @DisplayName("Find unexistent package by ID")
    void whenSearchingForUnexistentPackage_returnNone() {

        when(repository.existsById(p.getId())).thenReturn(false);

        assertThatThrownBy(() -> service.getPackageById(p.getId()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(String.format("There are no packages with this ID:%s", p.getId()));

        verify(repository, Mockito.times(0)).findById(Mockito.anyInt());
        verify(repository, Mockito.times(1)).existsById(Mockito.anyInt());

    }

    @Test
    @DisplayName("Find packages pendent")
    void testFindPackagesByStatus_whenStatusIsPendent() {
        List<Package> packages_res = new ArrayList<>();
        for (int i = 0; i < package_status.size(); i++) {
            if (package_status.get(package_status.keySet().toArray()[i]) == DeliveryStatusEnum.PENDENT) {
                packages_res.add((Package) package_status.keySet().toArray()[i]);
            }
        }

        when(repository.findByStatus(DeliveryStatusEnum.PENDENT)).thenReturn(packages_res);
        assertThat(service.getPackagesPendent())
                .isNotNull()
                .isEqualTo(packages_res);
        verify(repository, Mockito.times(1)).findByStatus(DeliveryStatusEnum.PENDENT);
    }

    @Test
    @DisplayName("Find packages accepted")
    void testFindPackagesByStatus_whenStatusIsAccepted() {
        List<Package> packages_res = new ArrayList<>();
        for (int i = 0; i < package_status.size(); i++) {
            if (package_status.get(package_status.keySet().toArray()[i]) == DeliveryStatusEnum.ACCEPTED) {
                packages_res.add((Package) package_status.keySet().toArray()[i]);
            }
        }
        when(repository.findByStatus(DeliveryStatusEnum.ACCEPTED)).thenReturn(packages_res);

        assertThat(service.getPackagesAccepted())
                .isNotNull()
                .isEqualTo(packages_res);
        verify(repository, Mockito.times(1)).findByStatus(DeliveryStatusEnum.ACCEPTED);

    }

    @Test
    @DisplayName("Find packages picked up")
    void testFindPackagesByStatus_whenStatusIsPickedUp() {
        List<Package> packages_res = new ArrayList<>();
        for (int i = 0; i < package_status.size(); i++) {
            if (package_status.get(package_status.keySet().toArray()[i]) == DeliveryStatusEnum.PICKED_UP) {
                packages_res.add((Package) package_status.keySet().toArray()[i]);
            }
        }
        when(repository.findByStatus(DeliveryStatusEnum.PICKED_UP)).thenReturn(packages_res);
        assertThat(service.getPackagesPickedUp())
                .isNotNull()
                .isEqualTo(packages_res);
        verify(repository, Mockito.times(1)).findByStatus(DeliveryStatusEnum.PICKED_UP);
    }

    @Test
    @DisplayName("Find packages on delivery")
    void testFindPackagesByStatus_whenStatusIsOnDelivery() {
        List<Package> packages_res = new ArrayList<>();
        for (int i = 0; i < package_status.size(); i++) {
            if (package_status.get(package_status.keySet().toArray()[i]) == DeliveryStatusEnum.ON_DELIVERY) {
                packages_res.add((Package) package_status.keySet().toArray()[i]);
            }
        }
        when(repository.findByStatus(DeliveryStatusEnum.ON_DELIVERY)).thenReturn(packages_res);
        assertThat(service.getPackagesOnDelivery())
                .isNotNull()
                .isEqualTo(packages_res);
        verify(repository, Mockito.times(1)).findByStatus(DeliveryStatusEnum.ON_DELIVERY);
    }

    @Test
    @DisplayName("Find packages delivered")
    void testFindPackagesByStatus_whenStatusIsDelivered() {
        List<Package> packages_res = new ArrayList<>();
        for (int i = 0; i < package_status.size(); i++) {
            if (package_status.get(package_status.keySet().toArray()[i]) == DeliveryStatusEnum.DELIVERED) {
                packages_res.add((Package) package_status.keySet().toArray()[i]);
            }
        }
        when(repository.findByStatus(DeliveryStatusEnum.DELIVERED)).thenReturn(packages_res);
        assertThat(service.getPackagesDelivered())
                .isNotNull()
                .isEqualTo(packages_res);
        verify(repository, Mockito.times(1)).findByStatus(DeliveryStatusEnum.DELIVERED);
    }

    @Test
    @DisplayName("Get package status")
    void whenGettingAPackageStatus_returnRightStatus() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(p));
        when(repository.existsById(p.getId())).thenReturn(true);

        assertThat(service.getPackageStatus(p))
                .isNotNull()
                .isEqualTo(p.getStatus());
        verify(repository, Mockito.times(1)).existsById(p.getId());
        
    }

    @Test
    void testWhenPackageIsUpdated_packageStatusIsUpdated() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(p));
        when(repository.existsById(p.getId())).thenReturn(true);

        DeliveryStatusEnum actual_state = p.getStatus();
        assertEquals(DeliveryStatusEnum.PENDENT, actual_state);

        // Accepting
        service.updatePackageStatus(p);
        
        assertThat(p.getStatus())
                .isNotNull()
                .isEqualTo(DeliveryStatusEnum.ACCEPTED);

        verify(repository, Mockito.times(1)).existsById(p.getId());

    }


/*     @Test
    void testAcceptingPackage_whenIsAValidRider() {
        Mockito.when(repository.findById(1)).thenReturn(p);
        when(repository.existsById(p.getId())).thenReturn(true);

        Mockito.when(riderRepository.findById(1)).thenReturn(rider);
        when(riderRepository.existsById(p.getId())).thenReturn(true);

        assertEquals(DeliveryStatusEnum.PENDENT, p.getStatus());

        // Accepting
        service.updatePackageStatus(p);
        
        assertThat(p.getStatus())
                .isNotNull()
                .isEqualTo(DeliveryStatusEnum.ACCEPTED);

        verify(repository, Mockito.times(1)).existsById(p.getId());

    } */
    


    // package has to have a valid rider accepting it

    // @Test
    // @DisplayName("")
    // void testAssociateRiderToPackage_whenInvalidRider()


    // Mockito.when(riderRepository.findByUsername("username_123")).thenReturn(Optional.empty());

    // package has to be delivered by a rider without a package atready

    // package update with valid rider

    // package update with error connection

    // package picked verify delivery time

}
