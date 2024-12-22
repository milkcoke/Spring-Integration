package milkcoke.core.service.order;

import lombok.RequiredArgsConstructor;
import milkcoke.core.annotation.MainDiscountPolicy;
import milkcoke.core.domain.discount.DiscountPolicy;
import milkcoke.core.domain.member.Member;
import milkcoke.core.domain.order.Order;
import milkcoke.core.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// order service 는 할인에 대한 것을 아무것도 알지 몬하는 상태다.
@Service
// final 키워드가 붙은 모든 프로퍼티에 대한 생성자를 만들어준다.
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    // 구현체가 아닌 Interface (역할) 에만 의존해야한다. => DIP 원칙 준수
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        var member = memberRepository.findById(memberId);
        var discountPrice = discountPolicy.getDiscountPrice(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
