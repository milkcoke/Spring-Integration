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
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("bookForm") BookForm bookForm) {
        // 당연히 여기서 ID를 품고있어야 하는거 아닌가?


        // 어설프게 entity 를 controller 에서 생성하지 말것.
        // Service 단에도 파라미터로 전체 엔티티가 아닌 필요한 property 만 명시하는게 좋다.
        // 그보다 복잡해질 것 같으면 DTO 를 정의하여 전달하는게 좋다.
        // TODO
        //  authorName 과 isbn 을 넘겨 업데이트 하고싶다면 어떻게 처리해야할까?
        itemService.updateItem(itemId, bookForm.getName(),bookForm.getPrice(), bookForm.getStockQuantity());
        return "redirect:/items";
    }


    @PostMapping("/items/new")
    public String create(BookForm form) {
        Book book = Book.createBook(form.getName(), form.getPrice(), form.getStockQuantity(), form.getAuthorName(), form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }
}
