/* package com.meds.deliveries.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.bouncycastle.crypto.util.Pack;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meds.deliveries.enums.DeliveryStatusEnum;
import com.meds.deliveries.enums.RiderStatusEnum;
import com.meds.deliveries.model.Ride;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.model.Package;
import com.meds.deliveries.repository.PackageRepository;
import com.meds.deliveries.repository.RiderRepository;
import com.meds.deliveries.security.auth.JWTTokenUtils;

@ExtendWith(MockitoExtension.class)
public class PackageServiceTest {
     
    @InjectMocks private PackageService service;

    @Mock( lenient = true ) private PackageRepository repository;
    private Package p;

    
    @Mock
    private RiderRepository riderRepository;

    @Mock
    private SpringUserDetailsService userDetailsService;
    

    @BeforeEach
    void setUp() {
        //this.rider = new Rider("John Doe", "johndoe", "mypassword", "john@doe.com", 912345678, "My house");
        this.p = new Package("40.631284 / N 40° 37' 52.623''","-8.659886 / W 8° 39' 35.591''", "Rua Dr. Mário Sacramento 12", "Joana Vedor", DeliveryStatusEnum.PENDENT, 1, 1 );
    }

    @AfterEach
    void cleanUp() { 
        reset(repository); 
    }

    //Test
    //void whenPackageAccepted_UpdateStatus

    //package has to have a valid rider accepting it
    @Test
    void testAssociateRiderToPackage_whenInvalidRider() {
        Mockito.when(riderRepository.findByUsername("username_123")).thenReturn(Optional.empty());

    }
    //package has to be delivered by a rider without a package altready

    //package update with valid rider

    //package update with error connection

    //package picked verify delivery time

}
 */