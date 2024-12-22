package milkcoke.core.autowired;

import milkcoke.core.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    static class TestBean {

        // 의존관계가 존재하지 않을 때, 메소드 자체가 아예 호출되지 않는다.
        @Autowired(required = false)
        public void setNoBean1(Member member1) {
            System.out.println("member1 = " + member1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member2) {
            System.out.println("member2 = " + member2);
        }

        @Autowired
        public void setNoBean2(Optional<Member> member3) {
            System.out.println("member3 = " + member3);
        }
    }

    @Test
    void autowiredOptionTest() {
        // TestBean 클래스에 대한 BeanDefinition 메타 데이터를 생성하고 Spring Bean 을 Spring Container 에 직접 등록
        // 현재는 아무런 Bean 정보가 등록되있지 않으므로 깡통 빈이 등록됨.
        var ac = new AnnotationConfigApplicationContext(TestBean.class);

    }
}
