package jpabook.jpashop.repository.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.member.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    // Entity Manager 에 의한 모든 데이터 변경은 항상 트랜잭션 내에서 이뤄져야한다.
    // SpringBoot 가 EntityManager 를 주입해줌.
    // spring-boot-starter-data-jpa 패키지에 의해.
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }
}
