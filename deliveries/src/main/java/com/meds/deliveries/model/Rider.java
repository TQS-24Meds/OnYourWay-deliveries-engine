package com.meds.deliveries.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meds.deliveries.enums.RiderStatusEnum;

import org.springframework.security.core.GrantedAuthority;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "rider")
public class Rider extends Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_rider")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rider_location")
    private Coordinates riderLocation;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "average_rating", nullable = false)
    private float average_rating;

    @Column(name = "num_reviews", nullable = false)
    private int num_reviews;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private RiderStatusEnum status;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "rider")
    @JsonIgnore
    private List<Ride> rides;

    @Builder
    public Rider(String name, String username, String password, String email, int phone, String permission, String address) {
        super(name, username, password, email, phone, permission);
        this.status = RiderStatusEnum.AVAILABLE;
        this.address = address;
        this.average_rating = 0;
        this.num_reviews = 0;
    }
    
}
