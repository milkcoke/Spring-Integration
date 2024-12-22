package milkcoke.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        var ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        var prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        var prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("싱글톤 + 프로토타입, 단일 프로토타입을 사용하게 되는 케이스.")
    void singletonClientUsePrototype() {
        var ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        var clientBean1 = ac.getBean(ClientBean.class);
        var count1 = clientBean1.addAndGetCount();
        assertThat(count1).isEqualTo(1);

        var clientBean2 = ac.getBean(ClientBean.class);
        assertThat(clientBean1).isNotSameAs(clientBean2);
    }

    @Component
    @Scope("singleton")
    static class ClientBean {
        // 이 빈의 생성 시점에 PrototypeBean 은 이미 DI 되어있다.
//        private final PrototypeBean prototypeBean;

        // DI 대신 Dependency Lookup (DL) 을 이용하는 방법
        // ObjectProvider 를 활용한다.
        // ObjectProvider 는 찾아주는 과정을 도와주는 인터페이스일 뿐이다.
        private final Provider<PrototypeBean> prototypeProvider;

        @Autowired
        public ClientBean(Provider<PrototypeBean> prototypeProvider) {
            this.prototypeProvider = prototypeProvider;
        }

        public int addAndGetCount() {
            // DL 만 사용.
            // 매번 ClientBean 에 주입될 PrototypeBean 을 찾아오기만 한다.
            var prototypeBean = prototypeProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }


    @Component
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init called" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy called " + this);
        }

//        @Override
//        public PrototypeBean get() {
//            return this;
//        }
    }
}
