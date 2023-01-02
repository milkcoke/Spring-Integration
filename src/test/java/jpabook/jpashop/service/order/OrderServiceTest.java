package jpabook.jpashop.service.order;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.address.Address;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderStatus;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


// 여기서는 JPA 까지 포함된 통합 테스트지만
// 더 좋은 test 는 method 만 유닛 테스트 하는 것임.
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        // given
        Member member = getMember();
        Book book = getBook("시골", 10_000, 10);

        var orderCnt = 3;
        // when
        // order 생성자 메소드 호출시 내부적으로 order 상태 'ORDER' 로 설정
        Long orderId = orderService.order(member.getId(), book.getId(), orderCnt);

        // then
        Order order = orderRepository.findOneById(orderId);
        assertEquals(OrderStatus.ORDER, order.getOrderStatus());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(10_000 * orderCnt, order.getTotalPrice());
        assertEquals(7, book.getStockQuantity());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        // given
        Member member = getMember();
        Book book = getBook("시골", 10_000, 10);

        // when
        // 재고량 10개를 넘는 주문 시도
        var orderQuantity = 11;

        // then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), book.getId(), orderQuantity);
        });
    }

    @Test
    public void 주문취소() throws Exception {
        // given
        var member = getMember();
        var book = getBook("시골", 10_000, 10);
        Long orderId = orderService.order(member.getId(), book.getId(), book.getStockQuantity());

        // when
        orderService.cancelOrder(orderId);

        // then
        Order order = orderRepository.findOneById(orderId);

        assertEquals(OrderStatus.CANCEL, order.getOrderStatus());
        assertEquals(10, book.getStockQuantity());
    }

    private Book getBook(String name, int price, int stockQuantity) {
        var book = Book.createBook(name, price, stockQuantity, null, null);
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member getMember() {
        var member = new Member();
        member.setUserName("falcon");
        member.setAddress(new Address("서울","강남", "15242"));
        em.persist(member);
        return member;
    }


}