package com.meds.deliveries.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.meds.deliveries.model.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StoreRepositoryTest extends RunTestContainer {

    @Autowired private TestEntityManager entityManager;

    @Autowired private StoreRepository repository;
    
    private Store meds;

    @BeforeEach
    void setUp() {
        Coordinates loc = new Coordinates(-39.80711, 151.71425);
        entityManager.persistAndFlush(loc);

        meds = new Store("24Meds", UUID.randomUUID(), loc);
        entityManager.persistAndFlush(meds);
    }

    @Test
    void whenFindStoreByValidId_thenReturnValidStore() {
        Store storeFound = repository.findById(meds.getId());

        assertThat( storeFound ).isEqualTo( meds );
        assertThat( storeFound ).isNotNull();
    }

    @Test
    void whenFindByInvalidId_thenReturnInvalidStore() {
        Integer invalidId = -1;
        Store storeFound = repository.findById(invalidId).orElse(null);

        assertThat( storeFound ).isNull();
    }
    
}
