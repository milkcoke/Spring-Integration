package jpabook.jpashop.repository.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    // Entity Manager 에 의한 모든 데이터 변경은 항상 트랜잭션 내에서 이뤄져야한다.
    // SpringBoot 가 EntityManager 를 주입해줌.
    // spring-boot-starter-data-jpa 패키지에 의해.
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        // DB Insert query 가 날라감.
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        // 1st: Return Type, 2nd: Primary key
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // jpql
        // sql 은 테이블 대상으로 쿼리하는 반면, jpql 은 객체를 Entity 객체를 대상으로 쿼리함.
        // 1st: query, 2nd: return type
        return em.createQuery("select m from Member m", Member.class)
                        .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.userName = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
