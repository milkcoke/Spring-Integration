package falcon.springpractice.repository;

import falcon.springpractice.domain.Member;

import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member);
    // 없으면 null 되는데
    // 그대로 null 반환하는 대신에 Optional 로 감싸서 반환하는게 국룰
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
