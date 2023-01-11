package com.example.jpapractice.domain.order;

import com.example.jpapractice.domain.item.Item;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "order_price")
    private int orderPrice;

    @Column(name = "order_count")
    private int orderCount;

    void mappingOrder(Order order) {
        this.order = order;
    }

    void mappingItem(Item item) {
        this.item = item;
    }
}
