package spring.core.service.member;

import spring.core.domain.member.Member;
import spring.core.domain.member.Grade;
import spring.core.repository.member.MemberRepository;
import spring.core.repository.member.MemoryMemberRepository;
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
