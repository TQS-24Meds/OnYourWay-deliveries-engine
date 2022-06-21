package com.meds.deliveries.repository;

import java.util.List;
import java.util.Optional;

import com.meds.deliveries.enums.RiderStatusEnum;
import com.meds.deliveries.model.Rider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Integer> {
    Optional<Rider> findById(int id);
    Optional<Rider> findByEmail(String email);
    Optional<Rider> findByUsername(String username);
    List<Rider> findByStatus(RiderStatusEnum status);
    boolean existsById(int id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);



    @Query(value="SELECT * FROM rider rid INNER JOIN person pe ON pe.id_person = rid.id_person WHERE pe.name like %:keyword%" , nativeQuery = true)
    List <Rider> findKeyword(@Param("keyword") String keyword);
}
