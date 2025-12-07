package spring.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {
    @Configuration
    @ComponentScan(
            // @Component 면 충분하기 때문에 필터를 사용할 일이 거의 없음.
            // Spring 기본 설정에 맞춰 사용하는 것이 가장 좋다.
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }


    @Test
    void filterScan() {
        var ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        var beanA = ac.getBean("beanA", BeanA.class);

        assertThat(beanA).isNotNull();
        assertThrows(NoSuchBeanDefinitionException.class, ()->ac.getBean("beanB", BeanB.class));
    }
}
