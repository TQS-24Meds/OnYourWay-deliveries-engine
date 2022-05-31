package com.meds.deliveries.model;

import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meds.deliveries.enums.DeliveryStatusEnum;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "package")
public class Package {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_package")
    private int id;

    // vem do api n sei como fazer
    @Column(name = "products")
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

    // @OneToOne
    // @Column(name = "ride_id")
    // private int ride_id;

    @OneToOne(mappedBy = "ride", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Ride ride;

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


    public Package() {}

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

    public int getId() { return this.id; }

    public ProductList getProducts() { return this.products; }

    public void setProducts(ProductList products) { this.products = products; }

    public float getClient_lat() { return this.client_lat; }

    public void setClient_lat(float client_lat) { this.client_lat = client_lat; }

    public float getClient_long() { return this.client_long; }

    public void setClient_long(float client_long) { this.client_long = client_long; }

    public String getClient_addr() { return this.client_addr; }

    public void setClient_addr(String client_addr) { this.client_addr = client_addr; }

    public float getPrice() { return this.price; }

    public void setPrice(float price) { this.price = price; }

    public DeliveryStatusEnum getDeliveryStatus() { return this.status; }

    public void setDeliveyStatus(DeliveryStatusEnum status) { this.status = status; }

    public Ride getRide() { return this.ride; }

    public void setRide(Ride ride) { this.ride = ride; }

    public int getOrder_id() { return this.order_id; }

    public void setOrder_id(int order_id) { this.order_id = order_id; }

    public int getRider_id() { return this.rider_id; }

    public void setRider_id(int rider_id) { this.rider_id = rider_id; }

    public int getStore_id() { return this.store_id; }

    public void setStore_id(int store_id) { this.store_id = store_id; }
   
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", products='" + getProducts() + "'" +
            ", client_lat='" + getClient_lat() + "'" +
            ", client_long='" + getClient_long() + "'" +
            ", client_addr='" + getClient_addr() + "'" +
            ", price='" + getPrice() + "'" +
            ", status='" + getStatus() + "'" +
            ", ride='" + getRide() + "'" +
            ", order_id='" + getOrder_id() + "'" +
            ", rider_id='" + getRider_id() + "'" +
            ", store_id='" + getStore_id() + "'" +
            "}";
    }

}
