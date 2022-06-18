package com.meds.deliveries.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import com.meds.deliveries.model.Admin;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdminRepositoryTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private AdminRepository adminRepository;

    Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin("John Doe", "johndoe", "johnpass", "john@doe.com", 911111111, Collections.emptyList());
        entityManager.persistAndFlush(admin);
    }


    @Test
    public void whenFindAdminByValidId_thenReturnValidAdmin() {
        Admin adminFound = adminRepository.findById(admin.getId()).orElse(null);;
        assertThat( adminFound ).isEqualTo( admin );
    }

    @Test
    public void whenFindRiderByInvalidId_thenReturnNull() {
        int invalidId = -1;
        Admin adminFound = adminRepository.findById(invalidId).orElse(null);
        assertThat( adminFound ).isNull();
    }

    @Test
    void whenFindAdminByValidEmail_thenReturnValidAdmin() {
        Admin adminFound = adminRepository.findByEmail(admin.getEmail()).orElse(null);

        assertThat( adminFound ).isEqualTo( admin );
        assertThat( adminFound ).isNotNull();
    }
    
    @Test
    void whenFindAdminByInvalidEmail_thenReturnNull() {
        String invalidEmail = "invalid@email.com";
        Admin adminFound = adminRepository.findByEmail(invalidEmail).orElse(null);

        assertThat( adminFound ).isNull();

    }
}

