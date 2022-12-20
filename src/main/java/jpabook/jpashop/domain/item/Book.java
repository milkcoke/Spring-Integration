package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("b")
@Getter @Setter
public class Book extends Item {
    private String authorName;
    private String isbn;

}
