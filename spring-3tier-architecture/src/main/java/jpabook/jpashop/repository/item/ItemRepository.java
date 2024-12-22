package jpabook.jpashop.repository.item;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        // ID가 생성되지 않은 새로운 객체
        if (item.getId() == null) {
            em.persist(item);
        } else {
            // 이미 존재하는 객체
            // (DB에 있는 객체를 가져와 Update 하는 것과 유사함)
            em.merge(item);
        }
    }

    public Item findOneById(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
