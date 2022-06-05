/* package com.meds.deliveries.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.nullValue;

import com.meds.deliveries.model.Person;
import com.meds.deliveries.model.Ride;

import java.util.Collections;
import java.util.List;

@DataJpaTest
public class RideRepositoryTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private RideRepository rideRepository;

    @Test
    public void whenFindRideByValidId_thenReturnValidRide() {
        Ride ride = new Ride();
        entityManager.persistAndFlush(ride);

        Ride rideFound = rideRepository.findById(ride.getId());
        assertThat( rideFound, is(ride) );
    }
}
 */