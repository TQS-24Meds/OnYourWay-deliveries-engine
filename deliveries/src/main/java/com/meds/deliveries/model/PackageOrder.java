package com.meds.deliveries.model;

import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meds.deliveries.enums.DeliveryStatusEnum;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "package_order")
public class PackageOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_package_order")
    private int id;

    @Column(name = "client_lat", nullable = false)
    private float client_lat; 

    @Column(name = "client_long", nullable = false)
    private float client_long; 

    @Column(name = "client_addr", nullable = false)
    private String client_addr;    

    @CreationTimestamp
    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatusEnum status;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Ride ride;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinTable(name = "package_ride", 
    //   joinColumns = 
    //     { @JoinColumn(name = "id_package", referencedColumnName = "id") },
    //   inverseJoinColumns = 
    //     { @JoinColumn(name = "id_ride", referencedColumnName = "id") })
    // @JsonIgnore
    // private Ride ride;


    // estes vem do API por isso n sei ainda como meter aqui, se fosse p criar as entidades order/rider/store era outra historia  
    @Column(name = "order_id", nullable = false)
    private int order_id;

    @Column(name = "rider_id", nullable = false)
    private int rider_id;

    @Column(name = "store_id", nullable = false)
    private int store_id;

    @UpdateTimestamp
    @Column(name = "finalizedts")
    private Timestamp finalizedts;

    // Package pending
    public PackageOrder(float client_lat, float client_long, String client_addr, float price, DeliveryStatusEnum status, int order_id, int store_id) {
        this.client_lat = client_lat;
        this.client_long = client_long;
        this.client_addr = client_addr;
        this.price = price;
        this.status = DeliveryStatusEnum.PENDENT;
        this.order_id = order_id;
        this.store_id = store_id;
    }

    // Package accepted
    public PackageOrder(float client_lat, float client_long, String client_addr, float price, DeliveryStatusEnum status, Ride ride, int order_id, int rider_id, int store_id) {
        this.client_lat = client_lat;
        this.client_long = client_long;
        this.client_addr = client_addr;
        this.price = price;
        this.status = DeliveryStatusEnum.ACCEPTED;
        this.ride = ride;
        this.order_id = order_id;
        this.store_id = store_id;
        this.rider_id = rider_id;
    }

}
