package com.example.jpapractice.domain.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Getter
// Use plural `orders` instead of `order` since there's probability by `ORDER BY` Syntax error
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @NonNull
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @PrePersist
    void PrePersist() {
        this.orderDate = LocalDateTime.now();
    }
}
