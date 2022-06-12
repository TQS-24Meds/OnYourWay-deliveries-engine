package com.meds.deliveries.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.enums.RiderStatusEnum;
import com.meds.deliveries.model.Ride;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.repository.RiderRepository;

@ExtendWith(MockitoExtension.class)
public class RiderServiceTest {
    
    @InjectMocks private RiderService service;

    @Mock( lenient = true ) private RiderRepository repository;

    private Rider rider;

    @BeforeEach
    void setUp() {
        this.rider = new Rider("John Doe", "johndoe", "mypassword", "john@doe.com", 912345678, Collections.emptyList(), "My house");
    }

    @AfterEach
    void cleanUp() { reset(repository); }

    @Test
    void whenSignUpRider_thenRiderIsRegistered() throws Exception {
        Rider newRider = service.registerRider(rider);
        assertThat(newRider).isEqualTo(rider);
    }

    @Test
    void whenUpdateLocation_thenLocationIsUpdated() throws Exception {
        Rider newRider = service.updateLocation(7.75404f, -15.95717f, rider);
        assertThat(7.75404f).isEqualTo(newRider.getLat());
        assertThat(-15.95717f).isEqualTo(newRider.getLon());
    }

    @Test
    void whenRequestRides_thenGetRides() throws Exception {
        List<Ride> allRides = new ArrayList<>();
        Package ride_package = new Package(36.58569f, 36.58569f, "Client address", "Client name", 5.0f, DeliveryStatusEnum.DELIVERED, 1, 1);
        Ride r1 = new Ride(ride_package, 5);
        
        allRides.add(r1);
        rider.setRides(allRides);

        given(repository.findById(rider.getId())).willReturn(rider);

        List<Ride> response = service.getAllRides(rider);

        assertEquals(allRides, response);
    }

    @Test
    void givenStatus_whenGetRiders_thenReturnRiders() throws Exception {
        
        List<Rider> allRiders = new ArrayList<>();
        
        Rider john = new Rider("John", "john", "mypassword", "john@user.com", 911111111, Collections.emptyList(), "John house");
        Rider alice = new Rider("Alice", "alice", "mypassword", "alice@user.com", 922222222, Collections.emptyList(), "Alice house");
        Rider alex = new Rider("Alex", "alex", "mypassword", "alex@user.com", 933333333, Collections.emptyList(), "Alex house");

        john.setStatus(RiderStatusEnum.AVAILABLE);
        alice.setStatus(RiderStatusEnum.AVAILABLE);
        alex.setStatus(RiderStatusEnum.UNAVAILABLE);

        allRiders.add(john);
        allRiders.add(alice);
        allRiders.add(alex);

        List<Rider> found = service.getRidersByStatus(RiderStatusEnum.AVAILABLE, allRiders);
        assertThat(found).hasSize(2);
    }

    @Test
    void givenRider_whenGetStatistics_thenReturnStatistics() throws Exception {
        rider.setAverage_rating(4.5f);
        rider.setNum_reviews(10);

        Map<String, Object> found = service.getRatingStatistics(rider);

        assertThat(found).containsEntry("numReviews", 10).containsEntry("avgReviews", 4.5f);
    }

}
