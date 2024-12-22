package milkcoke.core.service.order;

import milkcoke.core.domain.order.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
