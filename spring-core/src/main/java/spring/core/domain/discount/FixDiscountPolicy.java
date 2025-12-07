package spring.core.domain.discount;

import spring.core.domain.member.Grade;
import spring.core.domain.member.Member;
import org.springframework.stereotype.Component;

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
