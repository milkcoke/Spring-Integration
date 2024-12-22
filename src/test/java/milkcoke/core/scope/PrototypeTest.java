package milkcoke.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        var ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // init method 까지만 호출됨
        var prototypeBean1 = ac.getBean(PrototypeBean.class);
        var prototypeBean2 = ac.getBean(PrototypeBean.class);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);


        // 따라서 프로토타입 빈 사용 후에는 종료 전에 destroy() 메소드를 호출해야한다.
        prototypeBean1.destroy();
        prototypeBean2.destroy();
        ac.close();
    }

    @Component
    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("init method is called");
        }
        @PreDestroy
        public void destroy() {
            System.out.println("destroy method is called");
        }
    }
}
