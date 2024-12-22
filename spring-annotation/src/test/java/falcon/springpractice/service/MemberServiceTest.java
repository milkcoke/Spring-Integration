package falcon.springpractice.service;

import falcon.springpractice.domain.Member;
import falcon.springpractice.repository.MemoryMemberRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    private MemberService memberService;
    private MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        // Member Service 와 Test 부에서 동일한 Repository 를 사용하도록 강제함.
        // Dependency Injection
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
        memberRepository.initSequence();
    }


    @Test
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

        // try - catch 구문은 장황하다.
        //        try {
        //            memberService.join(member2);
        //            // 성공하면 안되는데 성공한경우 fail
        //            fail();
        //        } catch (IllegalStateException e) {
        //            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //        }


    }

    @Test
    void 모든_회원조회() {
    }

    @Test
    void 이름으로_회원조회() {
    }
}