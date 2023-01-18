package com.example.jpapractice.domain.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class MemberTest {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
    // EntityManager 는 객체를 대신 저장해줌.
    // 또 쓰레드간 절대 공유해선 안됨. 임시로 쓰고 버리는 객체임 (DB Connection 객체처럼)
    private EntityManager em;
    @Test
    void 회원가입_테스트() {

        var tx = getEntityTransaction();
        tx.begin();

        try {

            var member = Member.registerMember("크리링");
            em.persist(member);
//            var targetMember = em.find(Member.class, 1L);
//            persist 하자마자 영속성 컨텍스트에는 등록되었지만 아직 COMMIT 하지 않은 상태
//            따라서 DB에 실제로 레코드가 생성되지 않음.
//            DDL 에 의해 설정된 'Default' 값들 또한 적용되지 않은 상태
            // 실제로 flush 를 통해 데이터를 밀어 넣어야 DDL 의 default 값들을 반영함.


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Test
    void 등록_및_수정_테스트() {
        var tx = getEntityTransaction();

        try {
            tx.begin();
            var member = Member.registerMember("제이스");
            em.persist(member);
            member.changeMemberInfo("자르반", 25);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Test
    void 수정_테스트() {
        var tx = getEntityTransaction();

        try {
            tx.begin();
            var member = em.find(Member.class, 1L);
            member.changeMemberInfo("자르반", 26);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Test
    void 기본키맵핑_테스트() {
        var tx = getEntityTransaction();

        try {
            tx.begin();
            var member1 = Member.registerMember("제이스");
            var member2 = Member.registerMember("바드");
            var member3 = Member.registerMember("럼블");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Test
    void strategySequenceTest() {
        var tx = getEntityTransaction();

        try {
            tx.begin();
            var member1 = Member.registerMember("제이스");
            System.out.println("=============================");
            em.persist(member1);
            System.out.println("=============================");
            assertThat(member1.getId()).isEqualTo(1L);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Test
    void strategyIdentityTest() {
        var tx = getEntityTransaction();

        try {
            tx.begin();
            var member1 = Member.registerMember("제이스");
            System.out.println("=============================");
            em.persist(member1);
            System.out.println("=============================");
            assertThat(member1.getId()).isEqualTo(1L);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Test
    void registerAddress() {
        var tx = getEntityTransaction();
        tx.begin();

        try {
            var member = Member.registerMember("카르마");
            var address = new Address("Republic of Korea", "서울", "강남대로 15", "2932192");
            member.changeHomeAddress(address);

            em.persist(member);
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