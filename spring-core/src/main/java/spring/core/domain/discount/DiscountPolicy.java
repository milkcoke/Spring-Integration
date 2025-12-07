package spring.core.domain.discount;

import spring.core.domain.member.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int getDiscountPrice(Member member, int price);

}
