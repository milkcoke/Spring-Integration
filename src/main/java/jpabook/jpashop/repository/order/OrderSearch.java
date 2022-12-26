package jpabook.jpashop.repository.order;

import jpabook.jpashop.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName; // 회원명
    private OrderStatus orderStatus; // 주문 상태
}
