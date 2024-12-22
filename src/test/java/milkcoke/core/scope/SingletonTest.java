package milkcoke.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        // 싱글톤 클래스인 `SingletonBean` Component scan 등록
        // AnnotationConfigApplicationContext() 파라미터로 넘긴 클래스는 Component 스캔 대상으로 자동인식한다.
        var ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        var singletonBean1 = ac.getBean(SingletonBean.class);
        var singletonBean2 = ac.getBean(SingletonBean.class);
        assertThat(singletonBean1).isEqualTo(singletonBean2);

        ac.close();
    }

    @Component
    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {

        }
        @PreDestroy
        public void destroy() {
        }
    }

}
