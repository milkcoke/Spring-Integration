package jpabook.jpashop.repository.order;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOneById(Long id) {
        return em.find(Order.class, id);
    }

    // static query
    public List<Order> findAll(OrderSearch orderSearch) {
        // how to resolve dynamic query
        var orders = em.createQuery("SELECT o FROM Order o JOIN o.member m" +
                " WHERE o.status = :status" +
                " AND m.name LIKE :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                // LIMIT 1000
                .setMaxResults(1000)
                .getResultList();

        return orders;
    }


}
