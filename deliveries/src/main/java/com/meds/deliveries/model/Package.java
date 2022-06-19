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

 
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_coordinates")
    private Coordinates packageFinalLocation;

    @Column(name = "order_id", nullable = false)
    private int order_id;

    @Column(name = "rider_id", nullable = false)
    private int rider_id;


    @ManyToOne
    @JoinColumn(name="id_store")
    private Store store;


    @UpdateTimestamp
    @Column(name = "finalizedts")
    private Timestamp finalizedts;

    // Package pending
    //acho que o store id temos q alterar para store uuid
    public Package(String client_addr, String client_name, int order_id, Store store) {
        this.client_addr = client_addr;
        this.client_name = client_name;
        this.status = DeliveryStatusEnum.PENDENT;
        this.order_id = order_id;
        this.store = store;
        this.packageFinalLocation = store.getStore_location();
        //this.packageLocation 

    }


}
