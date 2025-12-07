package spring.core.service.order;

import milkcoke.core.domain.discount.DiscountPolicy;
import milkcoke.core.domain.discount.FixDiscountPolicy;
import milkcoke.core.domain.member.Grade;
import milkcoke.core.domain.member.Member;
import milkcoke.core.repository.member.MemberRepository;
import milkcoke.core.repository.member.MemoryMemberRepository;
import milkcoke.core.service.member.MemberService;
import milkcoke.core.service.member.MemberServiceImpl;
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
