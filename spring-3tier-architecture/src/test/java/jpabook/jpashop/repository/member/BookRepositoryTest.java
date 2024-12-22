package jpabook.jpashop.repository.member;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.item.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookRepositoryTest {
    private final BookRepository bookRepository;

    @Autowired
    public BookRepositoryTest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    public void cleanDB() {

    }
    @Test
    @Transactional(readOnly = false)
    public void testCreateBook() {
        Book book = Book.createBook("칭칭칭", 50_000, 5, "던말릭", "ShowMe");
        bookRepository.save(book);
        var bookId = book.getId();

        Book generatedBook = bookRepository.findOneById(bookId);
        assertThat(book).isEqualTo(generatedBook);
    }

    @Test
    @Transactional(readOnly = false)
    public void testUpdateBook() {
        Book book = Book.createBook("칭칭칭", 50_000, 5, "던말릭", "ShowMe");
        bookRepository.save(book);
        var bookId = book.getId();

        var targetBook = bookRepository.findOneById(bookId);
        assertThat(book).isEqualTo(targetBook);
        assertThat(book).isSameAs(targetBook);
        targetBook.changeBookInfo("칭칭칭", 33_333, 3, "던말릭", "showme2");

        System.out.println(bookRepository.findOneById(targetBook.getId()).getPrice());
    }
}
