package com.example.jpapractice.domain.item;

import com.example.jpapractice.domain.category.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

public class CategoryItemTest {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
    private EntityManager em;

    @Test
    void categoryParentChildTest() {
        var tx = getEntityTransaction();

        try {
            tx.begin();
            Category foodCategory = new Category("음식");
            Category fruitCategory = new Category("과일");

            Item apple = new Item("사과");
            Item banana = new Item("바나나");
            apple.updateItemInfo(500, 10);
            banana.updateItemInfo(1_000, 15);

            ItemCategory category1 = new ItemCategory(apple, fruitCategory);
            ItemCategory category2 = new ItemCategory(banana, fruitCategory);
            foodCategory.addChildCategory(fruitCategory);

            em.persist(foodCategory);
            em.persist(fruitCategory);
            em.persist(category1);
            em.persist(category2);
            em.persist(apple);
            em.persist(banana);

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
