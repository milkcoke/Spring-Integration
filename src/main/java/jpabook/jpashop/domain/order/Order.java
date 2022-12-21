package jpabook.jpashop.domain.order;

import jakarta.persistence.*;
import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.delivery.DeliveryStatus;
import jpabook.jpashop.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    // Foreign key name as `member_id`
    // Always set child entity as owner
    @JoinColumn(name = "member_id")
    private Member member;

    // 개별적인 orderItem 을 persist 하지 않아도 되게끔한다.
    // (내부적으로 persist 콜)
    // cascade 가 자동으로 전파되게끔 ALL 옵션을 설정해주었다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // property name
    private List<OrderItem> orderItems = new ArrayList();

    // 1:1 관계일 때는 Foreign key 를 어디에 두든 상관 없다.
    // 자주 액세스하는 부분에 FK 를 두는 것이 좋다. => 연관 관계의 주인.
    // Order 를 항상 조회하며 배송정보를 불러오므로 여기에 건다.
    // Order 내의 delivery 변경후 persist(orderA) 시 자동으로 해당 delivery 에도 persist 가 전파된다.
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDatetime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    // 연관관계 (편의) 메소드
    // 핵심적으로 컨트롤 하는 쪽이 들고있는 것이 좋다.
    public void setMember(Member member) {
        this.member = member; // 1개 order 는 1명의 member 에게 종속된다.
        member.getOrders().add(this); // member 가 보유한 모든 order 를 추가하는 방식
    }

    // 양방향 연관관계를 맞추기 위해 원자적으로 메소드 하나로 묶음. (양방향 연결)
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    /**
     * 생성 메서드
     */
     public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
         var order = new Order();
         order.setMember(member);
         order.setDelivery(delivery);
         for (var orderItem : orderItems) {
             order.addOrderItem(orderItem);
         }
         order.setOrderStatus(OrderStatus.ORDER);
         order.setOrderDatetime(LocalDateTime.now());

         return order;
     }

    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMPLETE) {
            throw new IllegalStateException("이미 배송 완료된 상품은 취소가 불가능합니다.");
        }
        this.setOrderStatus(OrderStatus.CANCEL);
        for (var orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    /**
     * 전체 주문 가격 조회
     * 한 주문 내에 여러 주문 상품이 존재할 수 있음.
     * 모든 주문 상품의 수량과 가격을 곱연산 하는 비즈니스 로직은 OrderItem 에 정의
     * ex) A책 3권, B책 5권
     */
    public int getTotalPrice() {
        var totalPrice = orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();

        return totalPrice;
    }
}
