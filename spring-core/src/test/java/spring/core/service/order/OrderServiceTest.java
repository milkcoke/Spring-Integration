package spring.core.service.order;

import spring.core.domain.discount.DiscountPolicy;
import spring.core.domain.discount.FixDiscountPolicy;
import spring.core.domain.member.Grade;
import spring.core.domain.member.Member;
import spring.core.repository.member.MemberRepository;
import spring.core.repository.member.MemoryMemberRepository;
import spring.core.service.member.MemberService;
import spring.core.service.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {
    private MemberService memberService;
    private OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        this.memberService = new MemberServiceImpl(memberRepository);
        DiscountPolicy discountPolicy = new FixDiscountPolicy();
        this.orderService = new OrderServiceImpl(memberRepository, discountPolicy);
    }

    @Test
    void createOrderTest() {
        Long memberId = 1L;
        var member = new Member(memberId, "memberA", Grade.VIP);
        this.memberService.join(member);

        var order = orderService.createOrder(memberId, "itemA", 10_000);

        assertThat(order.getDiscountPrice()).isEqualTo(1_000);
    }
}
