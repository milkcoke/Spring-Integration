package milkcoke.core.autowired;

import milkcoke.core.AutoAppConfig;
import milkcoke.core.domain.discount.DiscountPolicy;
import milkcoke.core.domain.member.Grade;
import milkcoke.core.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int getDiscountPrice(Member member, int price, String discountCode) {
            var discountPolicy = policyMap.get(discountCode);
            return discountPolicy.getDiscountPrice(member, price);
        }
    }

    @Test
    void findAllBean() {
        // Component Scan 을 하면서 fixDiscountPolicy, rateDiscountPolicy (camelCase) 모두 읽어들인다.
        // 모두 list, map 에 각각 DI 된다.
        var ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        var member = new Member(1L, "userA", Grade.VIP);
        // Component Scan 시 자동으로 camelCase 로 Spring Bean 을 Container 에 등록.
        var fixDiscountPrice = discountService.getDiscountPrice(member, 10_000, "fixDiscountPolicy");
        assertThat(fixDiscountPrice).isEqualTo(1_000);

        var rateDiscountPrice = discountService.getDiscountPrice(member, 20_000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2_000);
    }
}
