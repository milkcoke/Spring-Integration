package com.example.jpapractice.domain.item;

import com.example.jpapractice.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
// Item 만 봐서 어떤 서브타입으로 매핑해야할지 알기 위해서는 필요하다.
// 관례상 기본값인 'dtype'을 사용한다.
@DiscriminatorColumn(name = "data_type")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String name;

    @OneToMany(mappedBy = "item")
    @Column(name = "item_category_id")
    private final List<ItemCategory> itemCategories = new ArrayList<>();


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

    @PrePersist()
    public void prePersist() {
        this.updateCreateTimestamp("");
    }

    @PreUpdate()
    public void preUpdate() {
        this.updateModifiedTimestamp("");
    }
}
