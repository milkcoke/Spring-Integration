package com.example.jpapractice.domain.order;

import com.example.jpapractice.domain.BaseEntity;
import com.example.jpapractice.domain.delivery.Delivery;
import com.example.jpapractice.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
// Use plural `orders` instead of `order` since there's probability by `ORDER BY` Syntax error
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 양방향 연관관계는 권장되지 않지만 비즈니스 로직상
    // 주문으로부터 주문 상품을 조회하는 것은 자주 쓰이고 의미가 있음.
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    @NonNull
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne
    @JoinColumn(name = "delivery_id", unique = true)
    private Delivery delivery;

    /**
     * 연관관계 편의 메소드
     * @param orderItem
     */
    public void addOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.mappingOrder(this);
    }

    public void mappingMember(Member member) {
        this.member = member;
    }

    @PrePersist
    void PrePersist() {
        this.orderDate = LocalDateTime.now();
        if (this.member != null) this.status = OrderStatus.ORDER;
    }
}
