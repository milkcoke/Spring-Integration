package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("b")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book extends Item {
    private String authorName;
    private String isbn;

    public void changeBookInfo(String name, int price, int stockQuantity, String authorName, String isbn) {
        super.changeItemInfo(name, price, stockQuantity);
        this.authorName = authorName;
        this.isbn = isbn;
    }

    public static Book createBook(String name, int price, int stockQuantity, String authorName, String isbn) {
        Book book = new Book();
        book.changeBookInfo(name, price, stockQuantity, authorName, isbn);

        return book;
    }
}
