package com.example.jpapractice.domain.item;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String name;

    public Item(String name) {
        this.name = name;
    }
    private int price;
    private int stockQuantity;

    public void updateItemInfo(int price, int stockQuantity) {
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
