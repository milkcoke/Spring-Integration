package falcon.springpractice.repository;

import falcon.springpractice.domain.Member;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    // JPA 는 Entity Manager 로 모든게 동작.
    private final EntityManager em;

    // SpringBoot 가 자동으로 Entity Manager 연결해서 만들어줌.
    // Injection 받기만 하면 됨.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // Insert 하고 알아서 종료됨.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        var member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // jpql
        var members = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return members
                .stream()
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // alias m 인데 이거는 그냥 사용법 익혀야함..
        // 객체 자체를 선택하는 것.
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
