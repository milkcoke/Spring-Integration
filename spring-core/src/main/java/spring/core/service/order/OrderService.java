package spring.core.service.order;

import spring.core.domain.order.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
