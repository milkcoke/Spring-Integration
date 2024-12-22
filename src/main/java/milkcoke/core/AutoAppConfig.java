package milkcoke.core;

import milkcoke.core.repository.member.MemberRepository;
import milkcoke.core.repository.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 패키지를 찾을 시작 위치를 지정할 수 있음.
        basePackages = "milkcoke.core",
        // Configuration 붙은 클래스는 제외 (ex. AppConfig)하여 충돌 방지
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // DI 를 생성자 없이 어떻게 할 것인가? => AutoWired!

    // Overriding bean definition for bean 'memoryMemberRepository' with a different definition
    // : replacing [Generic bean: class [milkcoke.core.repository.member.MemoryMemberRepository]
    // 수동 등록 빈이 자동 등록 빈에 비해 우선권을 가지고 오버라이딩 해준다.
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memoryRepository() {
//        return new MemoryMemberRepository();
//    }
    // 비록 정상 실행되더라도, Bean 이름을 달리하여 중복 충돌을 회피하는 것이 좋다.
}
