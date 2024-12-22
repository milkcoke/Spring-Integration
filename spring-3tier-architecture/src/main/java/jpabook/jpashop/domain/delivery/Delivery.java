package jpabook.jpashop.domain.delivery;

import jakarta.persistence.*;
import jpabook.jpashop.domain.address.Address;
import jpabook.jpashop.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    private Address address;

    // Ordinal 은 순서가 바뀌면 장애가 날 수 있으므로
    // 되도록 String 을 사용하라.
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus; // Ready || Complete
}
