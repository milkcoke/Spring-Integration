package jpabook.jpashop.controller.item;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items")
    public String items(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("bookForm", new BookForm());
        return "items/createItemForm";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOneById(itemId);
        // 이렇게 하는게 짜증나긴 하지만 걍 해야함.
        BookForm bookForm = new BookForm();
        bookForm.setId(item.getId());
        bookForm.setName(item.getName());
        bookForm.setPrice(item.getPrice());
        bookForm.setStockQuantity(item.getStockQuantity());
        bookForm.setAuthorName(item.getAuthorName());
        bookForm.setIsbn(item.getIsbn());

        model.addAttribute("bookForm", bookForm);

        return "items/updateItemForm";
    }

    @PutMapping("items/{itemId}")
    // thymeleaf html 파일에서 object 이름이 그대로 넘어옴
    public String updateItem(@PathVariable String itemId, @ModelAttribute("bookForm") BookForm bookForm) {
        Book book = (Book) itemService.findOneById(Long.parseLong(itemId));
        // bookForm 은 대체 ID를 어떻게 가지고 있는거?
        book.setName(bookForm.getName());
        book.setPrice( bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthorName(bookForm.getAuthorName());
        book.setIsbn(bookForm.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items";
    }


    @PostMapping("/items/new")
    public String create(BookForm form) {
        Book book = Book.createBook(form.getName(), form.getPrice(), form.getStockQuantity(), form.getAuthorName(), form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }
}
