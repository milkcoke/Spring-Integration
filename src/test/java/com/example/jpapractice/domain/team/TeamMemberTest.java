package com.example.jpapractice.domain.team;

import com.example.jpapractice.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

public class TeamMemberTest {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
    private EntityManager em;

    // 항상 '멤버'만 필요한지
    // 멤버에 속한 '팀'도 필요한지

    // 비즈니스 로직상 멤버와 팀 모두 필요할지 멤버가 필요한지에 따라
    // 지연로딩, 즉시로딩, 프록시 같은 기법을 사용하게된다.

    // em.find 가 실제 Entity 객체를 조회하는 거라면
    // em.getReference 는 DB 조회를 미루는 가짜 Entity (Proxy) 를 반환한다.
    @Test
    void getMemberAndTeam() {
        initMemberAndTeam();
        var tx = getEntityTransaction();

        try {
            tx.begin();
            var firstMember = em.find(Member.class, 1L);
            System.out.println("======================");
            System.out.println(firstMember.getName());
            System.out.println("======================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Test
    void getMemberOnlyTest() {
        initMemberAndTeam();
        var tx = getEntityTransaction();

        try {
            tx.begin();
            var firstMember = em.getReference(Member.class, 1L);
            System.out.println("======================");
            // class com.example.jpapractice.domain.member.Member$HibernateProxy$HeHEZAPz
            System.out.println(firstMember.getClass());
            System.out.println(firstMember.getName());
            System.out.println("======================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private EntityTransaction getEntityTransaction() {
        this.em = emf.createEntityManager();
        return em.getTransaction();
    }


    void initMemberAndTeam() {

        var tx = getEntityTransaction();

        try {
            tx.begin();
            var member = Member.registerMember("카르마");
            var team = new Team("삼성 라이온즈");
            // 자식 먼저 넣고 부모 넣으면 각각 insert 1회 => 총 2회 INSERT
            // 후에 UPDATE (FOREIGN KEY) 까지 1회
            // 총 3회 쿼리가 발생함.
            em.persist(team);


            // 반면에 부모 먼저 넣고 자식 넣으면
            // 자식을 INSERT 할 때 FOREIGN KEY 를 한번에 넣으므로 2회 INSERT 로 모두 끝남.
            member.registerTeam(team);
            em.persist(member);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
