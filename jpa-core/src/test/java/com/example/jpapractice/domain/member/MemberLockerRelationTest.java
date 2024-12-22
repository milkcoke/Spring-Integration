package com.example.jpapractice.domain.member;

import com.example.jpapractice.domain.locker.Locker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberLockerRelationTest {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
    private EntityManager em;

    @Test
    void initMemberAndLocker() {
        var tx = getEntityTransaction();

        try {
            tx.begin();
            var member = Member.registerMember("카르마");
            var locker = new Locker("1번 사물함");
            member.changeMemberLocker(locker);


            em.persist(member); // 이렇게 해도 되나? 오류 안날듯, 영속성 컨텍스트에만 등록하는 거라.
            // 왜냐하면 아직 locker 는 DB에 없기 때문에
            System.out.println("지금 등록중 입니다.");
            // locker 를 영속성 컨텍스트에 등록
            em.persist(locker);

            // 영속성 컨텍스트에는 member - locker 모두 매핑정보가 들어있고
            // commit 하는 순간에 DB에 쿼리를 날릴 것이기 때문에 ^^

            // INSERT member, INSERT locker, Update member
            // 총 3차레 쿼리가 실행됨.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    @Test
    void readMemberMappedLocker() {
        initMemberAndLocker();

        var tx = getEntityTransaction();
        try {
            tx.begin();
            var member = em.find(Member.class, 1L);
            assertThat(member.getLocker().getName()).isEqualTo("1번 사물함");

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
