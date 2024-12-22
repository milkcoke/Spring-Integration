package falcon.springpractice.repository;

import falcon.springpractice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Interface 는 다중 상속 가능.
// Spring Data Jpa 가 구현체를 자동으로 만들어 Spring Bean 에 자동으로 등록해줌.
// 개발자는 가져다 쓰기만 하면됨.

// 자동으로 Repository 어노테이션 붙여짐.
@Repository
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL 이 findBy??? 에 오는 ???에 맞춰
    // SELECT m from Member m where m.??? = ? 로 쿼리를 짜준다.
    @Override
    Optional<Member> findByName(String name);
}
