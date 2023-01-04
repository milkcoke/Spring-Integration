package jpabook.jpashop.controller.order;

import jpabook.jpashop.repository.order.OrderSearch;
import jpabook.jpashop.service.item.ItemService;
import jpabook.jpashop.service.member.MemberService;
import jpabook.jpashop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        var orders = orderService.findOrders(orderSearch);
        // modelAttribute 는 자동으로 담기기 때문에 .addAttribute 호출이 필요하지 않다.
        // 앞서 회원명과 상태는 orderSearch 에 담겨온다.
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @GetMapping("/order")
    public String createForm(Model model) {
        var members = memberService.findMembers();
        var items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/orders")
    // Controller 는 식별자만 Service 에 넘기고 Entity 를 찾는 것부터 비즈니스 로직까지 모두 Service 에 위임하는게 좋다.
    // 미리 Entity 를 찾아서 넘기면 영속성 컨텍스트와 관계 없는 상태로 Service 에서 시작됨.
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @PatchMapping("/orders/{orderId}")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:orders";
    }
}
