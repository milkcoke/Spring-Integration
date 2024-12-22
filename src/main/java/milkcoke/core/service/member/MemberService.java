package milkcoke.core.service.member;

import milkcoke.core.domain.member.Member;

public interface MemberService {

    void join(Member member);

    Member findMemberById(Long memberId);
}
