package milkcoke.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatelessServiceTest {
    static class TestConfig {
        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }

    @Test
    @DisplayName("Stateless 서비스")
    void testStatelessService() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        var statelessService1 = ac.getBean(StatelessService.class);
        var statelessService2 = ac.getBean(StatelessService.class);

        assertThat(statelessService1).isSameAs(statelessService2);

        // 각기 다른 주문에 상태를 갖지 않게됨.
        var priceA = statelessService1.order("userA", 10_000);
        var priceB = statelessService2.order("userB", 20_000);

        // 독립된 주문 금액이 반환됨.
        // 싱글톤으로 같은 인스턴스 객체를 사용하나 가격 정보 상태를 갖지 않음.
        assertThat(priceA).isNotEqualTo(priceB);
    }

}