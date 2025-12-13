package spring.core.discount;

import spring.core.domain.discount.RateDiscountPolicy;
import spring.core.domain.member.Grade;
import spring.core.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Test
    @DisplayName("VIP 는 10% 할인이 적용되야한다.")
    void vipDiscountCheck() {
        var member = new Member(1L, "memberA", Grade.VIP);

        int discountedPrice = discountPolicy.getDiscountPrice(member, 10_000);
        assertThat(discountedPrice).isEqualTo(1_000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야한다.")
    void normalDiscountCheck() {
        var member = new Member(1L, "memberA", Grade.BASIC);

        int discountedPrice = discountPolicy.getDiscountPrice(member, 10_000);
        assertThat(discountedPrice).isEqualTo(0);
    }
}
