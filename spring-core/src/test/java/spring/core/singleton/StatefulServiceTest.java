package spring.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        var statefulService1 = ac.getBean(StatefulService.class);
        var statefulService2 = ac.getBean(StatefulService.class);

        assertThat(statefulService1).isSameAs(statefulService2);

        // ThreadA: A 사용자가 10,000원 주문
        // ThreadB: B 사용자가 20,000원 주문
        statefulService1.order("userA", 10_000);
        statefulService2.order("userB", 20_000);

        // 사용자A가 주문 금액 조회
        // 같은 인스턴스 객체에서 공유 변수 'price' 를 수정했으므로 가장 나중에 발생한 트랜잭션인 B의 결과가 반영됨.
        assertThat(statefulService1.getPrice()).isNotEqualTo(10_000);
    }
}
