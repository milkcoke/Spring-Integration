package jpabook.jpashop.service.item;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.item.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional(readOnly = false)
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional(readOnly = false)
    public void updateBook(Long itemId, String name, int price, int stockQuantity, String authorName, String isbn) {
        Book foundBook = bookRepository.findOneById(itemId);
        foundBook.changeBookInfo(name, price, stockQuantity, authorName, isbn);
    }

    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    public Item findOneById(Long id) {
        return bookRepository.findOneById(id);
    }
}

