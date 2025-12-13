package spring.core.singleton;

import spring.core.config.AppConfig;
import spring.core.service.member.MemberService;
import spring.core.service.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        var memberService = ac.getBean("memberService", MemberService.class);
        var orderService = ac.getBean("orderService", OrderService.class);
    }

    @Test
    void configurationDeepTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 부모: spring.core.config.AppConfig, 자식: spring.core.config.AppConfig$$SpringCGLIB
        var bean = ac.getBean(AppConfig.class);

        // bean = class spring.core.config.AppConfig$$SpringCGLIB$$0
        // 클래스 이름이 복잡해진 이유는
        // @Configuration 어노테이션에 의해 CGLIB 이라는 바이트코드가 조작 라이브러리를 사용하여
        // spring.core.config.AppConfig 를 상속받아 별도의 클래스를 스프링 컨테이너 안에 등록한 것이다.
        // 이름은 appConfig 지만 spring.core.config.AppConfig$$SpringCGLIB 이 온 이유는 그것 때문이다.
        // 이 임의의 다른 크래스가 싱글톤을 보장해준다.
        System.out.println("bean = " + bean.getClass());
    }
}
