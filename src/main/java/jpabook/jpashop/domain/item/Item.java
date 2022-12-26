package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.category.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Joined: 정규화된 스타일
// Single Table: 한 테이블에 때려박기
// Table Per class: 실존 하는 테이블 생성
@DiscriminatorColumn
@Getter @Setter
// 싱글 테이블 전략
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList();

    // 재고 증감 비즈니스 로직
    // DDD 면에서 Entity 내에서 해결할 수 있는 것은
    // Entity 내에 포함시키는 것이 좋다.
    // 재고라는 데이터 (property) 를 가진 entity 내에 메소드를 위치하는 것이 좋은 도메인 설계다.

    /**
     * stock 증가
     */
    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int stockQuantity) {
        var restStockQuantity = this.stockQuantity - stockQuantity;
        if (restStockQuantity < 0) {
            throw new NotEnoughStockException("Need more remain stock quantity");
        }

        this.stockQuantity = restStockQuantity;
    }

}
