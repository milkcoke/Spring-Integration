package jpabook.jpashop.service.member;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    // JUnit5 에서는 DI를 스스로 지원하므로 field DI 를 해야만함.
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;
    @Test
    public void 회원가입() {
        // given
        var member = new Member();
        member.setUserName("Falcon");

        // when
        var savedId = memberService.join(member);
        entityManager.flush();
        // then
        assertThat(member).isEqualTo(memberRepository.findById(savedId));
        assertThat(member).isSameAs(memberRepository.findById(savedId));
    }

    // 아래
    @Test
    public void 중복_회원_예외() {
        // given
        var member1 = new Member();
        member1.setUserName("u na kim");
        var member2 = new Member();
        member2.setUserName("u na kim");
        // when
        memberService.join(member1);

        // then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

    }
}