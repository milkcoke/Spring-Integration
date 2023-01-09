package com.example.jpapractice.domain.order;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_item_id")
    private Long id;

    private Long orderId;
    private Long itemId;

    @Column(name = "order_price")
    private int orderPrice;

    @Column(name = "order_count")
    private int orderCount;

}
