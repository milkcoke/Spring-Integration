package milkcoke.core.repository.member;

import milkcoke.core.domain.member.Member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
