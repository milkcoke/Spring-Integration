package spring.core.repository.member;

import spring.core.domain.member.Member;

public interface MemberRepository {
  void save(Member member);

  Member findById(Long memberId);
}
