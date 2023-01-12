package com.example.jpapractice.domain.item;

import com.example.jpapractice.domain.category.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "item")
    @Column(name = "item_category_id")
    private List<ItemCategory> itemCategories = new ArrayList<>();


    public Item(String name) {
        this.name = name;
    }
    private int price;
    private int stockQuantity;

    public void updateItemInfo(int price, int stockQuantity) {
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void addItemCategory(ItemCategory itemCategory) {
        itemCategories.add(itemCategory);
    }
}
