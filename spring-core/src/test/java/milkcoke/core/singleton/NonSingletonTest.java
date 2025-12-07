package milkcoke.core.singleton;

import milkcoke.core.config.AppConfig;
import milkcoke.core.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NonSingletonTest {
    @Test
    @DisplayName("Pure DI Container")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회 : 호출 할 때마다 객체를 생성하는지 검증
        MemberService memberService1 = appConfig.memberService();

        // 2. 2번째 조회로 객체 생성 여부 확인
        MemberService memberService2 = appConfig.memberService();

        // 서로 달라 JVM 메모리에 지속적으로 새 인스턴스가 생성됨.
        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

    }
}
