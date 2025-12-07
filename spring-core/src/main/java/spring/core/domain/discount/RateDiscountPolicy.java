package spring.core.domain.discount;

import spring.core.domain.member.Grade;
import spring.core.domain.member.Member;

//@Component
//@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

  private final int discountPercent = 10;

  @Override
  public int getDiscountPrice(Member member, int price) {
    if (member.grade() == Grade.VIP) {
      return (int) (price * discountPercent * 0.01);
    }
    return 0;
  }
}
