/* package com.meds.deliveries.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.nullValue;

import com.meds.deliveries.enums.RiderStatusEnum;
import com.meds.deliveries.model.Rider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@DataJpaTest
class RiderRepositoryTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private RiderRepository repository;

    @Test
    public void whenFindRiderByValidId_thenReturnValidRider() {
        Rider rider = new Rider("John Doe", "johndoe", "mypassword", "john@doe.com", 912345678, Collections.emptyList(), "My house");
        entityManager.persistAndFlush(rider);

        Rider riderFound = repository.findById(rider.getId());
        assertThat( riderFound, is(rider) );
    }

    @Test
    public void whenFindRiderByInvalidId_thenReturnNull() {
        int invalidId = -1;
        Rider riderFound = repository.findById(invalidId);
        assertThat( riderFound, is(nullValue()) );
    }

    @Test
    public void whenFindRidersByStatus_thenReturnValidRiders() {
        Rider john = new Rider("John", "john", "mypassword", "john@user.com", 911111111, Collections.emptyList(), "John house");
        Rider alice = new Rider("Alice", "alice", "mypassword", "alice@user.com", 922222222, Collections.emptyList(), "Alice house");
        Rider alex = new Rider("Alex", "alex", "mypassword", "alex@user.com", 933333333, Collections.emptyList(), "Alex house");

        alice.setStatus(RiderStatusEnum.AVAILABLE);
        alex.setStatus(RiderStatusEnum.AVAILABLE);

        entityManager.persist(john);
        entityManager.persist(alice);
        entityManager.persist(alex);
        entityManager.flush();

        List<Rider> ridersFound = repository.findByStatus(RiderStatusEnum.AVAILABLE);

        assertThat( ridersFound.size(), is(2) );
        assertThat( ridersFound.contains(john), is(false) );
        assertThat( ridersFound.contains(alice), is(true) );
        assertThat( ridersFound.contains(alex), is(true) );
    }

}
 */