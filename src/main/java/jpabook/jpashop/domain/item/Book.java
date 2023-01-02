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

    protected Book() {

    }

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
