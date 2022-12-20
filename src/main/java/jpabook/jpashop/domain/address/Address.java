package jpabook.jpashop.domain.address;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // JPA 구현 라이브러리가 객체 생성시
    // 리플랙션 같은 기술을 사용할 수 있도록 지원해야 하기 때문에
    // 기본 생성자를 둬야한다.
    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
