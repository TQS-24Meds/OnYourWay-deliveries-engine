package com.meds.deliveries.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.nullValue;

import com.meds.deliveries.enums.RiderStatusEnum;
import com.meds.deliveries.model.Person;
import com.meds.deliveries.model.Rider;

import java.util.List;

@DataJpaTest
class RiderRepositoryTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private RiderRepository riderRepository;

    @Test
    public void whenFindRiderByValidId_thenReturnValidRider() {
        Rider rider = new Rider(new Person("John Doe", "johndoe", "mypassword", "john@doe.com", 912345678),
             "My house", 4.9f, 10, RiderStatusEnum.UNAVAILABLE, null);
        entityManager.persistAndFlush(rider);

        Rider riderFound = riderRepository.findById(rider.getId());
        assertThat( riderFound, is(rider) );
    }

    @Test
    public void whenFindRiderByInvalidId_thenReturnNull() {
        int invalidId = -1;
        Rider riderFound = riderRepository.findById(invalidId);
        assertThat( riderFound, is(nullValue()) );
    }

    @Test
    public void whenFindRidersByStatus_thenReturnValidRiders() {
        Rider john = new Rider(new Person("John", "john", "mypassword", "john@user.com", 911111111),
             "John house", 4.5f, 10, RiderStatusEnum.UNAVAILABLE, null);
        Rider alice = new Rider(new Person("Alice", "alice", "mypassword", "alice@user.com", 922222222),
            "Alice house", 4.0f, 10, RiderStatusEnum.UNAVAILABLE, null);
        Rider alex = new Rider(new Person("Alex", "alex", "mypassword", "alex@user.com", 933333333),
            "Alex house", 4.8f, 10, RiderStatusEnum.UNAVAILABLE, null);

        alice.setStatus(RiderStatusEnum.AVAILABLE);
        alex.setStatus(RiderStatusEnum.AVAILABLE);

        entityManager.persist(john);
        entityManager.persist(alice);
        entityManager.persist(alex);
        entityManager.flush();

        List<Rider> ridersFound = riderRepository.findByStatus(RiderStatusEnum.AVAILABLE);

        assertThat( ridersFound.size(), is(2) );
        assertThat( ridersFound.contains(john), is(false) );
        assertThat( ridersFound.contains(alice), is(true) );
        assertThat( ridersFound.contains(alex), is(true) );
    }

}
