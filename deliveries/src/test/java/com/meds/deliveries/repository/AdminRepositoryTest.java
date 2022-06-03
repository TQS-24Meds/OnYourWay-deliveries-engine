package com.meds.deliveries.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.nullValue;

import com.meds.deliveries.model.Admin;
import com.meds.deliveries.model.Person;

@DataJpaTest
class AdminRepositoryTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private AdminRepository adminRepository;

    @Test
    public void whenFindAdminByValidId_thenReturnValidAdmin() {
        Admin admin = new Admin(new Person("John Admin", "admin", "admin1234", "admin@store.com", 91234567));
        entityManager.persistAndFlush(admin);

        Admin adminFound = adminRepository.findById(admin.getId());
        assertThat( adminFound, is(admin) );
    }

    @Test
    public void whenFindRiderByInvalidId_thenReturnNull() {
        int invalidId = -1;
        Admin adminFound = adminRepository.findById(invalidId);
        assertThat( adminFound, is(nullValue()) );
    }
}

