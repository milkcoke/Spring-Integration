package spring.core.beanfind;

import spring.core.config.AppConfig;
import spring.core.service.member.MemberService;
import spring.core.service.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        var memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        // 타입 조회시 같은 타입이 여러개일 때 문제가 발생할 수 있으므로 이름으로 조회하길 추천한다.
        var memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 구체적 타입으로 조회")
    void findBeanByNameAndClass() {
        // spring.core.config.AppConfig 에 구현체가 등록되어 있기 때문에 DIP 원칙을 깨뜨리긴함.. => Interface 로 조회하는게 원래는 국룰
        var memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX() {
//        MemberService memberService =
        // 2nd param: lambda expression
        // 람다식이 1st 유형의 예외를 던져야한다는 뜻.
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));

    }
}
