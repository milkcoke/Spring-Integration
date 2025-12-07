package spring.core.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.core.domain.discount.DiscountPolicy;
import spring.core.domain.order.Order;
import spring.core.repository.member.MemberRepository;

// order service 는 할인에 대한 것을 아무것도 알지 몬하는 상태다.
@Service
// final 키워드가 붙은 모든 프로퍼티에 대한 생성자를 만들어준다.
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
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
