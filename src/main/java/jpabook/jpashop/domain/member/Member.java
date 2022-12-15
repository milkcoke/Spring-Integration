package jpabook.jpashop.domain.member;

import jakarta.persistence.*;
import jpabook.jpashop.domain.address.Address;
import jpabook.jpashop.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String userName;

    @Embedded
    private Address address;

    // 연관 관계의 주인이 아닌 경우 읽기 전용이 됨.
    // `member` field in order 에 의해 매핑되었다는 것을 명시.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList();
}

