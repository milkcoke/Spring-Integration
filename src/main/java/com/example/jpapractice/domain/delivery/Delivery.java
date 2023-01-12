package com.example.jpapractice.domain.delivery;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Column(length = 63)
    private String city;
    @Column(length = 63)
    private String street;
    @Column(length = 31)
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
