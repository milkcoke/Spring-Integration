package com.example.jpapractice.domain.category;

import com.example.jpapractice.domain.BaseEntity;
import com.example.jpapractice.domain.item.ItemCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name", nullable = false, length = 31)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> childCategories = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<ItemCategory> itemCategories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    /**
     * 연관관계 편의 메소드
     * @param childCategory
     */
    public void addChildCategory(Category childCategory) {
        this.childCategories.add(childCategory);
        childCategory.parent = this;
    }


}
