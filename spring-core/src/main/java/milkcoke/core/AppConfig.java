package milkcoke.core;

import milkcoke.core.domain.discount.DiscountPolicy;
import milkcoke.core.domain.discount.FixDiscountPolicy;
import milkcoke.core.repository.member.MemberRepository;
import milkcoke.core.repository.member.MemoryMemberRepository;
import milkcoke.core.service.member.MemberService;
import milkcoke.core.service.member.MemberServiceImpl;
import milkcoke.core.service.order.OrderService;
import milkcoke.core.service.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;

// 일종의 기획자 역할로
// 구현체를 연결해줄 애들을 여기서 결정한다.
// 수동 빈 등록방식
//@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        // 생성자 주입
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());
    }

    // 역할에 대한 구현부가 조금은 보이게 해줘야한다.
    // '메모리 멤버 리포지토리' 를 쓴다는 구현부가 드러남.
    @Bean
    public MemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    // '고정 할인율 정책'을 쓴다는 구현부가 드러남.
    @Bean
    public DiscountPolicy getDiscountPolicy() {
        return new FixDiscountPolicy();
    }
}

