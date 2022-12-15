package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("m")
@Getter @Setter
public class Movie extends Item {
    private String artistName;
    private String etc;
}
