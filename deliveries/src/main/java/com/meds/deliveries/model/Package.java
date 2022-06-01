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
@Table(name = "package")
public class Package {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_package")
    private int id;

    // vem do api n sei como fazer
    @Column(name = "products")
    // private ProductList products;
    private ProductList products;

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
    public Package(int id, ProductList products, float client_lat, float client_long, String client_addr, float price, DeliveryStatusEnum status) {
        this.id = id;
        this.products = products;
        this.client_lat = client_lat;
        this.client_long = client_long;
        this.client_addr = client_addr;
        this.price = price;
        this.status = DeliveryStatusEnum.PENDENT;
    }

    // Package accepted
    public Package(int id, ProductList products, float client_lat, float client_long, String client_addr, float price, DeliveryStatusEnum status, Ride ride, int order_id, int rider_id, int store_id) {
        this.id = id;
        this.products = products;
        this.client_lat = client_lat;
        this.client_long = client_long;
        this.client_addr = client_addr;
        this.price = price;
        this.status = DeliveryStatusEnum.ACCEPTED;
        this.ride = ride;
        this.order_id = order_id;
        this.rider_id = rider_id;
        this.store_id = store_id;
    }

}
