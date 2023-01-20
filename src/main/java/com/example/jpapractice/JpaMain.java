package com.example.jpapractice;

import com.example.jpapractice.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.Arrays;


public class JpaMain {
    @Transactional
    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
        // EntityManager 는 객체를 대신 저장해줌.
        // 또 쓰레드간 절대 공유해선 안됨. 임시로 쓰고 버리는 객체임 (DB Connection 객체처럼)
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // try ~ catch 구문은 JPA 가 기본적으로 매커니즘을 제공하기 때문에 불필요함.
        try {

            // JPA 의 모든 데이터 변경은 Transaction 내에서 실행해야한다.
            tx.begin();

            // 비영속 상태
//            var member = Member.registerMember("새로운 멤버");

            /**
             * 비영속 -> 영속 상태
             * 실제 DB에 저장되는게 아니라 영속성 컨텍스트에 저장.
             */
//            em.persist(member);
            // 영속성 컨텍스트에서 관리되는 객체는 변경 감지로 인해 COMMIT 시점에 flush 되며 수정 내역이 반영된다.

            Member foundMember = em.find(Member.class, 1L);
            System.out.println("멤버 이름 : " + foundMember.getName());
            foundMember.changeMemberInfo("블라디", 27);

            // jpql 은 엔티티 객체를 대상으로 쿼리한다 (DB 타입이 달라져도 자동으로 매핑되어 대응된다.)
            // SQL 은 DB 테이블을 대상으로 쿼리를 한다.
            // 영속성 컨텍스트에 저장된 정보를 바탕으로 DB 에 쿼리를 날린다.
            tx.commit();
        } catch (Exception e) {
            System.out.println("========Error occurs!========");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("=============================");
            tx.rollback();
        } finally {
            em.close();
        }

        // connection pooling 리소스 반납
        emf.close();

    }


}
