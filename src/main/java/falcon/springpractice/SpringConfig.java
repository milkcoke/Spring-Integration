package falcon.springpractice;

import falcon.springpractice.repository.JdbcTemplateMemberRepository;
import falcon.springpractice.repository.JpaMemberRepository;
import falcon.springpractice.repository.MemberRepository;
import falcon.springpractice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

// Open-Close Principal (OCP) : 확장에는 열려있고, 변경에는 닫혀있다.
@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
//    @PersistenceContext
//    private final EntityManager em;

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // Dependencies Injection 으로
    // 기존의 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//        return
//    }
}
