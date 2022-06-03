// package com.meds.deliveries.repository;

// import java.util.List;

// import com.meds.deliveries.enums.DeliveryStatusEnum;
// import com.meds.deliveries.model.PackageOrder;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface PackageOrderRepository extends JpaRepository<PackageOrder, Integer> {
//     PackageOrder findById(int id);
//     List<PackageOrder> findByStatus(DeliveryStatusEnum status);
//     List<PackageOrder> findAll();
// }
