package milkcoke.core.beanfind;

import milkcoke.core.domain.discount.DiscountPolicy;
import milkcoke.core.domain.discount.FixDiscountPolicy;
import milkcoke.core.domain.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy getFixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
        @Bean
        public DiscountPolicy getRateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
    }

    @Test
    @DisplayName("부모 타입 조회시, 자식이 둘 이상 있으면 중복 오류 발생")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입 조회시, 자식이 둘 이상 있으면 빈 이름으로 유니크함을 보장할 수 있다.")
    void findBeanByParentTypeWithBeanName() {
        var bean = ac.getBean("getFixDiscountPolicy", DiscountPolicy.class);
        assertThat(bean).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("구현 타입으로 조회")
    void findBeanBySubType() {
        var bean = ac.getBean(FixDiscountPolicy.class);
        assertThat(bean).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByparentType() {
        var beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType() {
        var beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }
}
