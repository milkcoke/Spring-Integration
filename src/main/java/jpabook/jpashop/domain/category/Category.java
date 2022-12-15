package jpabook.jpashop.domain.category;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    @Column(name = "category_name")
    private String name;

    // ManyToMany 는 매핑테이블에 컬럼 추가가 불가능하므로 왠만하면 쓰지 않는게 좋다.
    @ManyToMany
    // RDBMS 상에서 다대다는 Mapping table 필수
    @JoinTable(
            name = "category_item",
            // Mapping table 의 (PK, FK)
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList();

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList();
}
