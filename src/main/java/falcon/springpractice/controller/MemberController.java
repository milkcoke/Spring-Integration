package falcon.springpractice.controller;

import falcon.springpractice.domain.Member;
import falcon.springpractice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// Controller 가 Service 를 의존하여 View 에 데이터를 전달한다.
// 컨트롤러 -> 서비스의 의존관계가 존재할 수 밖에 없게된다.

// annotation 을 보고 Spring 이 Bean 이라는 객체를 만들어 보관함.
@Controller
public class MemberController {
    // Member Controller 객체를 생성해서 넣어둠.
    private final MemberService memberService;

    // Spring Container 에서 받아서 쓰드록 바꿔야함.
    // 다른 Controller 들도 Member Service 를 사용한다든다
    // 이러면 memberService 가 별도의 자원이 되버림.
    // memberService 는 딱 하나만 필요함!

    // member Controller 가 뜰 때 (생성할 때)
    // Spring 이 Container 속에 보관한 Service 를 자동으로 연결시켜줌.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/join")
    public String memberJoinForm() {
        return "members/memberJoinForm";
    }

    @PostMapping("/members/join")
    public String joinMember(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        var memberList = memberService.findMembers();
        model.addAttribute("members", memberList);

        // package name / *.html file name
        return "members/members";
    }
}
