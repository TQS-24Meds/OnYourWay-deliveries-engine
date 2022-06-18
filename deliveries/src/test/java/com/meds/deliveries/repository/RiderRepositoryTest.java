package com.meds.deliveries.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.meds.deliveries.enums.RiderStatusEnum;
import com.meds.deliveries.model.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RiderRepositoryTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private RiderRepository repository;

    Rider john;

    @BeforeEach
    void setUp() {
        john = new Rider("John Doe", "johndoe", "johnpass", "john@doe.com", 911111111, Collections.emptyList(), "riders house");
        entityManager.persistAndFlush(john);
    }

    @Test
    public void whenFindRiderByValidId_thenReturnValidRider() {
        Rider riderFound = repository.findById(john.getId()).orElse(null);;
        assertThat( riderFound ).isEqualTo( john );
    }

    @Test
    public void whenFindRiderByInvalidId_thenReturnNull() {
        int invalidId = -1;
        Rider riderFound = repository.findById(invalidId).orElse(null);;
        assertThat( riderFound ).isNull();
    }

    @Test
    public void whenGetAllRiders_thenReturnAllRiders() {
        Rider alice = new Rider("Alice", "alice", "mypassword", "alice@user.com", 922222222, Collections.emptyList(), "Alice house");
        Rider alex = new Rider("Alex", "alex", "mypassword", "alex@user.com", 933333333, Collections.emptyList(), "Alex house");
        
        entityManager.persist(alice);
        entityManager.persist(alex);
        entityManager.flush();

        List<Rider> allRiders = repository.findAll();

        assertThat( allRiders ).hasSize(3).extracting(Rider::getUsername).contains(john.getUsername(), alice.getUsername(), alex.getUsername());
        assertThat( allRiders ).contains(john);
        assertThat( allRiders ).contains(alice);
        assertThat( allRiders ).contains(alex);
    }

    @Test
    public void whenFindRidersByStatus_thenReturnValidRiders() {
        Rider alice = new Rider("Alice", "alice", "mypassword", "alice@user.com", 922222222, Collections.emptyList(), "Alice house");
        Rider alex = new Rider("Alex", "alex", "mypassword", "alex@user.com", 933333333, Collections.emptyList(), "Alex house");

        alice.setStatus(RiderStatusEnum.AVAILABLE);

        entityManager.persist(alice);
        entityManager.persist(alex);
        entityManager.flush();

        List<Rider> availableRiders = repository.findByStatus(RiderStatusEnum.AVAILABLE);

        assertThat( availableRiders ).hasSize(1);
        assertThat( availableRiders ).contains(alice);
    }

}
