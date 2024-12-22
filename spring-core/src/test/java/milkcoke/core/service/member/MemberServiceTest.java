package milkcoke.core.service.member;

import milkcoke.core.domain.member.Member;
import milkcoke.core.domain.member.Grade;
import milkcoke.core.repository.member.MemberRepository;
import milkcoke.core.repository.member.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MemberServiceTest {
  private MemberService memberService;

   @BeforeEach
  public void beforeEach() {
      MemberRepository memberRepository = new MemoryMemberRepository();
      memberService = new MemberServiceImpl(memberRepository);
  }

  @Test
  void join() {
      // given
      Member member = new Member(1L, "memberA", Grade.VIP);
      // when
      memberService.join(member);
      Member foundMember = memberService.findMemberById(1L);
       // then
      assertThat(member).isEqualTo(foundMember);
  }
}
