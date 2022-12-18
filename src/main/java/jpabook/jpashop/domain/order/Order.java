package jpabook.jpashop.domain.order;

import jakarta.persistence.*;
import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    // Foreign key name as `member_id`
    // Always set child entity as owner
    @JoinColumn(name = "member_id")
    private Member member;

    // 개별적인 orderItem 을 persist 하지 않아도 되게끔한다.
    // (내부적으로 persist 콜)
    // cascade 가 자동으로 전파되게끔 ALL 옵션을 설정해주었다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // property name
    private List<OrderItem> orderItems = new ArrayList();

    // 1:1 관계일 때는 Foreign key 를 어디에 두든 상관 없다.
    // 자주 액세스하는 부분에 FK 를 두는 것이 좋다. => 연관 관계의 주인.
    // Order 를 항상 조회하며 배송정보를 불러오므로 여기에 건다.
    // Order 내의 delivery 변경후 persist(orderA) 시 자동으로 해당 delivery 에도 persist 가 전파된다.
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDatetime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
