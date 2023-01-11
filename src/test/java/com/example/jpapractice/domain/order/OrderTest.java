package com.example.jpapractice.domain.order;

import com.example.jpapractice.domain.item.Item;
import com.example.jpapractice.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

class OrderTest {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
    // EntityManager 는 객체를 대신 저장해줌.
    // 또 쓰레드간 절대 공유해선 안됨. 임시로 쓰고 버리는 객체임 (DB Connection 객체처럼)
    private EntityManager em;
    @BeforeEach
    void beforeEach() {
        initData();
    }

    @Test
    @Transactional
    void initData() {
        var tx = getEntityTransaction();

        tx.begin();
        var member = Member.registerMember("럼블");
        var itemBanana = new Item("바나나");
        var itemApple = new Item("사과");
        itemBanana.updateItemInfo(5_000, 10);
        itemApple.updateItemInfo(1_000, 5);


        em.persist(member);
        em.persist(itemBanana);
        em.persist(itemApple);

//        Order
        var order = new Order();
        var orderItemBanana = new OrderItem();
        var orderItemApple = new OrderItem();
        orderItemBanana.mappingItem(itemBanana);
        orderItemBanana.mappingOrder(order);

        orderItemApple.mappingItem(itemApple);
        orderItemApple.mappingOrder(order);

        order.addOrderItems(orderItemBanana);
        order.addOrderItems(orderItemApple);

        order.mappingMember(member);
        // order 먼저 추가해도 문제 안생기나? (부모쪽인데?)
        em.persist(order);
        em.persist(orderItemApple);
        em.persist(orderItemBanana);
        tx.commit();
    }

    @Test
    void orderScenarioTest() {
        var tx = getEntityTransaction();


        try {
            tx.begin();
            var member = em.find(Member.class, 1L);
            var order = em.find(Order.class, 1L);
            System.out.println("order's member: " + order.getMember().getName());
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