package spring.core.domain.discount;

import org.springframework.stereotype.Component;
import spring.core.domain.member.Grade;
import spring.core.domain.member.Member;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

  private final int discountFixAmount = 1_000;

  @Override
  public int getDiscountPrice(Member member, int price) {
    if (member.grade() == Grade.VIP) {
      return discountFixAmount;
    } else {
      return 0;
    }
  }

}
