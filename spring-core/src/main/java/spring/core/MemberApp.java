package spring.core;

import spring.core.config.AppConfig;
import spring.core.domain.member.Grade;
import spring.core.domain.member.Member;
import spring.core.service.member.MemberService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    static void main(String[] args) {

        // 모든 객체 Bean 을 관리함
        // Spring Container 에 집어 넣어 관리함.
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 메서드 이름으로 name 이 설정되어있음. (key: 메소드명, value: 생성된 객체)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member foundMember = memberService.findMemberById(1L);

        System.out.println("Member: " + member.name());
        System.out.println("Found member: " + foundMember.name());

    }
}
