package spring.core.service.member;

import spring.core.domain.member.Member;

public interface MemberService {

    void join(Member member);

    Member findMemberById(Long memberId);
}
