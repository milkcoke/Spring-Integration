package falcon.springpractice.service;

import falcon.springpractice.domain.Member;
import falcon.springpractice.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberIntegrationServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Commit
    void 회원가입() {
        // 3단 구성 포맷으로 해라.

        // given => 무엇이 주여졌나
        var member = new Member();
        member.setName("milkcoke");

        // when => 이런 상황에서
        var savedId = memberService.join(member);

        // then => 이런 값이 나와야한다.
        var foundMember = memberService.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(foundMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        // given
        var member1 = new Member();
        member1.setName("milkcoke");
        var member2 = new Member();
        member2.setName("milkcoke");

        // when
        memberService.join(member1);

        // lambda 부에서 예외가 터져야만 한다는 것을 명시
        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }

    @Test
    void 모든_회원조회() {
    }

    @Test
    void 이름으로_회원조회() {
    }
}
