package spring.core.xml;

import milkcoke.core.service.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        // resources directory 를 자동으로 읽음.
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        var memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
