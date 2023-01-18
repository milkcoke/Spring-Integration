package com.example.jpapractice.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable // 임베디드 타입 선언 필수
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    @Column(length = 63)
    private String country;
    private String city;
    private String street;
    private String zipCode;

    public Address(String country, String city, String street, String zipCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
