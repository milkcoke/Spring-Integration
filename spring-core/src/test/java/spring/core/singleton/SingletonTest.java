package spring.core.singleton;

import lombok.Getter;
import spring.core.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @returns 싱글톤 객체
 */
public class SingletonTest {
    // 이미 생성된 객체를 불러오기만함.
    // static -> 클래스가 메모리에 로드될 때 생성됨.
    // instance 에 미리 넣어둠. (생성자 호출 필요 X)
    @Getter
    private static final SingletonTest instance = new SingletonTest();

    // private 키워드를 통해 추가 객체 생성을 방지한다.
    private SingletonTest() {}

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        var singletonTest1 = SingletonTest.getInstance();
        var singletonTest2 = SingletonTest.getInstance();

        // isEqual => Equality 검증
        // isSame => Identity 검증
        assertThat(singletonTest1).isSameAs(singletonTest2);
    }

    @Test
    @DisplayName("Spring Container & Singleton")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(spring.core.config.AppConfig.class);

        // 싱글톤 패턴이기 때문에 같은 Reference 를 참조함.
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
