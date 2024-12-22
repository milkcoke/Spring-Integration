package com.example.jpapractice;

import com.example.jpapractice.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceTest {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
    // EntityManager 는 객체를 대신 저장해줌.
    // 또 쓰레드간 절대 공유해선 안됨. 임시로 쓰고 버리는 객체임 (DB Connection 객체처럼)
    private EntityManager em;

    @Test
    void detachedContextTest() {
        EntityTransaction tx = getEntityTransaction();

        try {
            tx.begin();
//          Cache miss -> write member having id `2L` to persistence context
            var member = em.find(Member.class, 2L);
            member.changeMemberInfo("Narr", 20);

            // 영속 -> 준영속 컨텍스트
//            em.detach(member);
            tx.commit();
        } catch (Exception e) {
            System.out.println("========Error occurs!========");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("=============================");
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private EntityTransaction getEntityTransaction() {
        em = emf.createEntityManager();
        return em.getTransaction();
    }

}