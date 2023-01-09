package com.example.jpapractice.domain.item;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String name;
    private int price;
    private int stockQuantity;
}
