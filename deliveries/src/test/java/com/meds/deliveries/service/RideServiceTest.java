package com.meds.deliveries.service;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Ride;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.model.Store;
import com.meds.deliveries.model.Coordinates;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.repository.PackageRepository;
import com.meds.deliveries.repository.RideRepository;
import com.meds.deliveries.repository.RiderRepository;
import com.meds.deliveries.repository.StoreRepository;

@ExtendWith(MockitoExtension.class)
public class RideServiceTest {

    @InjectMocks
    private RideService service;

    @Mock(lenient = true)
    private RiderService riderService;

    @Mock(lenient = true)
    private RideRepository repository;
    private Ride ride;
    List<Ride> allRides = new ArrayList<>();

    @Mock(lenient = true)
    private PackageRepository packageRepository;
    private Package ride_package;

    @Mock(lenient = true)
    private StoreRepository storeRepository;
    private Store store;
    
    

    @Mock(lenient = true)
    private RiderRepository riderRepository;
    private Rider rider;




    @BeforeEach
    void setUp() {
        this.rider = new Rider("John Doe", "johndoe", "mypassword", "john@doe.com", 912345678, Collections.emptyList(),
                "My house");
        Mockito.when(riderRepository.save(rider)).thenReturn(rider);

        this.store = new Store("24 Meds", UUID.randomUUID(),  new Coordinates(87.2,87.1));
        
        this.ride_package = new Package(
                "Rua Dr. Mário Sacramento 12", "Joana Vedor", 1, store);
        this.ride = new Ride(ride_package);


        allRides.add(ride);

        rider.setRides(allRides);
        Mockito.when(packageRepository.save(ride_package)).thenReturn(ride_package);
        Mockito.when(repository.getById(1)).thenReturn(ride);
        Mockito.when(riderRepository.getById(1)).thenReturn(rider);
        Mockito.when(repository.save(ride)).thenReturn(ride);

        rider.setId(1);
        ride.setId(1);


    }

    @Test
    @DisplayName("Find all rides.")
    void findAllRides() {
        when(repository.findAll()).thenReturn(allRides);

        assertThat(service.getAllRides())
                .isNotNull()
                .isEqualTo(allRides);

        verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Find ride by id.")
    void findRideById() {
        when(repository.findById(ride.getId())).thenReturn(ride);
        when(repository.existsById(ride.getId())).thenReturn(true);

        assertThat(service.getRideById(ride.getId()))
                .isNotNull()
                .isEqualTo(ride);

        verify(repository, Mockito.times(1)).findById(Mockito.anyInt());
        verify(repository, Mockito.times(1)).existsById(Mockito.anyInt());

    }

    @Test
    @DisplayName("Find ride by id when nonexisting id.")
    void findRideById_whenInvalidId() {
        when(repository.existsById(ride.getId())).thenReturn(false);

        assertThatThrownBy(() -> service.getRideById(ride.getId()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(String.format("There is no ride with this id %s", ride.getId()));

        verify(repository, Mockito.times(0)).findById(Mockito.anyInt());
        verify(repository, Mockito.times(1)).existsById(Mockito.anyInt());

    }

    @Test
    @DisplayName("Find rides from a rider.")
    void whenRequestRidesFromRider_thenGetRides() {
        
        //get servico rider get id rider
        System.out.println(rider.getId());
        Rider riderFound = riderService.getRiderById(rider.getId());


        System.out.println("MAYBE" + riderFound.getRides());

        given(riderRepository.findById(rider.getId())).willReturn(Optional.of(rider));
        when(riderRepository.existsById(ride.getId())).thenReturn(true);

        assertThat(service.getAllRidesFromRider(rider))
                .isNotNull()
                .isEqualTo(allRides);

    }

    @Test
    @DisplayName("Find rides from a nonexistent rider.")
    void whenRequestRidesFromInvalidRider_thenGetNoRides() {
        when(riderRepository.existsById(rider.getId())).thenReturn(false);

        assertThatThrownBy(() -> service.getAllRidesFromRider(rider))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(String.format("There are no rides for this rider %s, because he doesn't exist", rider));
        
        
        verify(repository, Mockito.times(0)).findById(Mockito.anyInt());
        verify(riderRepository, Mockito.times(0)).findById(Mockito.anyInt());
        verify(riderRepository, Mockito.times(1)).existsById(Mockito.anyInt());
    }

    

    @Test
    void whenNewRideIsCreated_thenReturnRideInit() {
        service.rideInit(ride_package);
        assertEquals(ride_package.getStatus(), DeliveryStatusEnum.ON_DELIVERY);

    }

}
