package com.example.jpapractice.domain.item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
    // EntityManager 는 객체를 대신 저장해줌.
    // 또 쓰레드간 절대 공유해선 안됨. 임시로 쓰고 버리는 객체임 (DB Connection 객체처럼)
    private EntityManager em;

    void initBookData() {
        var tx = getEntityTransaction();
        tx.begin();

        try {

            Book book1 = new Book();
            book1.updateItemInfo(8_000, 1_000);
            book1.changeBookInfo("정청", "asdfas02321");
            em.persist(book1);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
    @Test
    void bookCreateTest() {
        initBookData();

        var tx = getEntityTransaction();
        tx.begin();

        try {
            Book itemBook = em.find(Book.class, 1L);
            System.out.println(itemBook.getName());
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