package spring.core.service.order;

import milkcoke.core.domain.discount.FixDiscountPolicy;
import milkcoke.core.domain.member.Grade;
import milkcoke.core.domain.member.Member;
import milkcoke.core.repository.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        // setter 주입시 순수 자바코드로 테스트 코드를 작성할 때
        // 의존관계가 전혀 드러나지 않음.

        // 생성자 주입을 사용하면 IDE 단에서 경고메시지를 준다.
        var memoryMemberRepository = new MemoryMemberRepository();
        var orderServiceImpl = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());

        var member = new Member(5L, "khazix", Grade.VIP);
        memoryMemberRepository.save(member);

        var order = orderServiceImpl.createOrder(5L, "itemA", 10_000);
        assertThat(order.getDiscountPrice()).isEqualTo(1_000);
    }
}
