package com.example.jpapractice.domain.member;

import com.example.jpapractice.domain.team.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberTeamRelationTest {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
    private EntityManager em;

    @Test
    void 팀과_멤버_생성() {
        var tx = getEntityTransaction();

        try {
            tx.begin();
            Team team = new Team("수원FC");
            em.persist(team);
            var foundTeam1 = em.find(Team.class, 1L);

            var team2 = new Team("서울홀롤롤");
            em.persist(team2);

            Member member = Member.registerMember("이천수");
            member.changeMemberTeam(team);
            em.persist(member);

            Member foundMember = em.find(Member.class, member.getId());
            assertThat(foundMember.getTeam().getId()).isEqualTo(foundTeam1.getId());
            assertThat(foundMember.getTeam().getName()).isEqualTo(foundTeam1.getName());

            member.changeMemberTeam(team2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Test
    void 특정팀의_모든팀원목록_조회() {
        var tx = getEntityTransaction();

        try {
            tx.begin();

            var member = Member.registerMember("가렌");
            var team = new Team("전사들");

            // 연관관계 주인이 아닌 team 쪽에 member 를 추가해봤자
            // FK 값에는 영향이 없다.
            // 읽기 전용이기 때문에
            team.getMembers().add(member); // 얘가 없어도 사실 팀을 가져오면

            em.persist(member);
            em.persist(team);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    @Test
    void 양쪽객체에_모두_넣어야해() {
        var tx = getEntityTransaction();

        try {
            tx.begin();

            var member = Member.registerMember("나르");
            var team = new Team("전사들");

            team.getMembers().add(member); // 1
            member.changeMemberTeam(team); // 2

            em.persist(member);
            em.persist(team);

            Team foundTeam = em.find(Team.class, team.getId()); // 1차 캐시, 쿼리가 날아가지 않음
            List<Member> members = foundTeam.getMembers();

            // 결론적으로 양쪽에 값을 세팅해주는게 맞다.
            System.out.println("==============================================");
            for (var m : members) {
                // 쿼리가 날아가지 않았기 때문에 해당 팀에 속하는 모든 멤버를 불러오지 못함.
                // 아무것도 출력되지 않음.
                System.out.println("member = " + m.getName());
            }
            System.out.println("==============================================");

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Test()
    void proxyNotInitialize() {
        var tx = getEntityTransaction();
        tx.begin();

        try {
            var referenceMember = em.getReference(Member.class, 1L);
            em.detach(referenceMember); // 준영속 컨텍스트로 전환
            System.out.println(referenceMember.getName()); // 영속성 컨텍스트에서 관리되지 않는 프록시 사용 시도
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Test()
    void firstFindSecondGetReference() {
        var tx = getEntityTransaction();
        tx.begin();
        try {

            // Entity 조회 후 getReference 시 실제 Entity object 반환
            var foundMember = em.find(Member.class, 1L);
            System.out.println("foundMember class: " + foundMember.getClass());


            var referenceMember = em.getReference(Member.class, 1L);
            System.out.println("Member class: " + referenceMember.getClass());

            System.out.println(referenceMember.getClass() == foundMember.getClass());
            System.out.println(referenceMember instanceof Member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Test()
    void firstGetReferenceSecondFind() {
        var tx = getEntityTransaction();
        tx.begin();
        try {


            var referenceMember = em.getReference(Member.class, 1L);
            System.out.println("referenceMember class: " + referenceMember.getClass());
            System.out.println(referenceMember.getName());

            // Entity 조회 후 getReference 시 실제 Entity object 반환
            var foundMember = em.find(Member.class, 1L);
            System.out.println("foundMember class: " + foundMember.getClass());

            System.out.println(referenceMember.getClass() == foundMember.getClass());
            System.out.println(referenceMember instanceof Member);
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
}
