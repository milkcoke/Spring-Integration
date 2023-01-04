package jpabook.jpashop.service.order;

import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderItem;
import jpabook.jpashop.repository.item.ItemRepository;
import jpabook.jpashop.repository.member.MemberRepository;
import jpabook.jpashop.repository.order.OrderRepository;
import jpabook.jpashop.repository.order.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문 생성
     * @return orderId
     */
    @Transactional(readOnly = false)
    public Long order(Long memberId, Long itemId, int count) {
        var member = memberRepository.findById(memberId);
        var item = itemRepository.findOneById(itemId);

        // 배송정보 생성
        var delivery = new Delivery();
        // 로직 단순화, 실제로는 회원이 직접 입력할 필요가 있음.
        delivery.setAddress(member.getAddress());

        // 주문상품 생성 by 생성 메소드 호출
        var orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        var order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        // delivery, orderItem 등에 save() 를 호출하지 않고도 처리되는 이유?
        // @OneToX 관계에서 cascade 옵션이 걸려있기 때문이다.
        orderRepository.save(order);

        return order.getId();

    }
    /**
     * 취소
     */
    @Transactional(readOnly = false)
    public void cancelOrder(Long orderId) {
        var order = orderRepository.findOneById(orderId);
        // 사실 cancel 하나 안에 재고 업데이트, 주문 상품 상태 cancel 업데이트를 모두 쿼리를 짜서 실행해야한다.
        // 그러나 JPA 사용시 Dirty checking 메커니즘에 의해 변경된 내역을 찾아 데이터베이스에 업데이트 쿼리를 알아서 날려준다.
        // 여기서는 Order, OrderItem 의 상태 Update 쿼리가 Dirty checking 후에 실행된다.
        order.cancel();
    }

    /**
     * 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
    
}
