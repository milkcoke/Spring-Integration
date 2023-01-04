package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("b")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Book extends Item {
    private String authorName;
    private String isbn;

    public static Book createBook(String name, int price, int stockQuantity, String authorName, String isbn) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        book.setAuthorName(authorName);
        book.setIsbn(isbn);

        return book;
    }
}
