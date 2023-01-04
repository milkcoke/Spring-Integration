package jpabook.jpashop.controller.item;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {
    // item properties
    private Long id;
    @NotEmpty(message = "Book name is required")
    private String name;
    private int price;
    private int stockQuantity;

    // book properties
    @NotEmpty(message = "Author name is required")
    private String authorName;
    private String isbn;
}
