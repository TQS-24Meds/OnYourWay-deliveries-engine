package com.meds.deliveries.model;

import java.sql.Timestamp;
import java.util.Date;

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
public class Package {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_package_order")
    private int id;

    @Column(name = "client_lat", nullable = false)
    private Double client_lat; 

    @Column(name = "client_long", nullable = false)
    private Double client_long; 

    @Column(name = "client_addr", nullable = false)
    private String client_addr;    

    @Column(name = "client_name", nullable = false)
    private String client_name;    

    @CreationTimestamp
    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatusEnum status;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Ride ride;

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
    public Package(Double client_lat, Double client_long, String client_addr, String client_name, DeliveryStatusEnum status, int order_id, int store_id) {
        this.client_lat = client_lat;
        this.client_long = client_long;
        this.client_addr = client_addr;
        this.client_name = client_name;
        this.status = DeliveryStatusEnum.PENDENT;
        this.order_id = order_id;
        this.store_id = store_id;
    }

    // Package accepted isto vai desaparecer
    public Package(Double client_lat, Double client_long, String client_addr, String client_name, DeliveryStatusEnum status, Ride ride, int order_id, int rider_id, int store_id) {
        this.client_lat = client_lat;
        this.client_long = client_long;
        this.client_addr = client_addr;
        this.client_name = client_name;
        this.status = DeliveryStatusEnum.ACCEPTED;
        this.ride = new Ride();
        this.order_id = order_id;
        this.store_id = store_id;
        this.rider_id = rider_id;
    }

}
