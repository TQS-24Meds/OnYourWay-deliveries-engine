package com.meds.deliveries.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.model.Coordinates;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.model.Store;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PackageRepositoryTest extends RunTestContainer {
 
    @Autowired private TestEntityManager entityManager;

    @Autowired private PackageRepository repository;
    
    private Store meds;
    private Package p1, p2;

    @BeforeEach
    void setUp() {
        meds = new Store("24Meds", UUID.randomUUID(), new Coordinates(-39.80711, 151.71425));
        entityManager.persistAndFlush(meds);

        p1 = new Package("Rua Colorida", "António Pinto", 1, meds);
        p2 = new Package("Rua Não Colorida", "Paulo António", 2, meds);
        p2.setStatus(DeliveryStatusEnum.ON_DELIVERY);
        entityManager.persistAndFlush(p1);
        entityManager.persistAndFlush(p2);

        List<Package> currentPackages = new ArrayList<>(Arrays.asList(p1, p2));

        assertThat(currentPackages).hasSize(2).extracting(Package::getOrder_id).contains(p1.getOrder_id(), p2.getOrder_id());
    }

    @Test
    void whenFindPackageByValidId_thenReturnValidPackage() {
        Package packageFound = repository.findById(p1.getId()).orElse(null);

        assertThat( packageFound ).isEqualTo( p1 );
        assertThat( packageFound ).isNotNull();
    }

    @Test
    void whenFindByInvalidId_thenReturnInvalidPackage() {
        Integer invalidId = -1;
        Package packageFound = repository.findById(invalidId).orElse(null);

        assertThat( packageFound ).isNull();
    }

    @Test
    void whenGetAllPackages_ThenReturnAllPackages(){
        Package p1Found = repository.findById(p1.getId()).orElse(null);
        Package p2Found = repository.findById(p2.getId()).orElse(null);
        assertThat( p1Found ).isEqualTo( p1 );
        assertThat( p2Found ).isEqualTo( p2 );

        List<Package> onDeliveryPackages = repository.findByStatus(DeliveryStatusEnum.ON_DELIVERY);

        assertThat( onDeliveryPackages ).hasSize(1).contains(p2);
    }

}
