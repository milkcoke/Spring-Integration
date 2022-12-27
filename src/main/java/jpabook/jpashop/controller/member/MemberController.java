package jpabook.jpashop.controller.member;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.address.Address;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        // Controller -> View 넘어갈 때 model 에 담긴 값을 넘김.
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    // Spring 이 annotation 을 참조해줌.
    // 오류가 있을 때 controller 에서 팅겨버리지 않고 validate 하고나서 오류가 담겨 실행됨.
    public String createMember(@Valid MemberForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // spring-thymeleaf 에 의해 원래 페이지에 에러 메시지를 랜더링해줌.
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        Member member = new Member();
        member.setUserName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }
}
