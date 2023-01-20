package com.example.jpapractice.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Embeddable // 임베디드 타입 선언 필수
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    @Column(length = 63)
    private String country;
    @Column(length = 31)
    private String city;
    @Column(length = 63)
    private String street;
    @Column(length = 15)
    private String zipCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // 메소드인 getter 를 통해 호출해야 프록시 객체에서도 정상 동작한다.
        // equals, hashCode 가 아닌 경우에도 왠만하면 직접 field 를 사용하기보다, getter 를 사용하라.
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCountry(), address.getCountry()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getZipCode(), address.getZipCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getCity(), getStreet(), getZipCode());
    }

    public Address(String country, String city, String street, String zipCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
