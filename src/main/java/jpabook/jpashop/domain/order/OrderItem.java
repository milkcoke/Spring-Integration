package jpabook.jpashop.domain.order;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    // 생성자 메소드만을 사용하여 생성 로직을 통일하게끔
    // protected 로 일반 생성자 사용을 방지해야한다.
    protected OrderItem() {

    }
    private int orderPrice; // 주문 당시 가격
    private int orderCnt; // 주문 수량

    /**
     * orderPrice 는 쿠폰 적용 등에 의해 달라질 수 있음
     * => 결코 item 에 종속적이지 않음.
     */
    public static OrderItem createOrderItem(Item item, int orderPrice, int orderCnt) {
        var orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setOrderCnt(orderCnt);

        // 재고 소진
        item.removeStock(orderCnt);
        return orderItem;
    }

    /**
     * If order created, there's plural items in the order
     * When cancel the order, orderItem must handle the item stockQuantity
     */
    public void cancel() {
        getItem().addStock(orderCnt);
    }

    public int getTotalPrice() {
        return orderPrice * orderCnt;
    }
}
