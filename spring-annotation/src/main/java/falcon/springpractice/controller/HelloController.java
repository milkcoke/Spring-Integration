package falcon.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // hello 호출시 메소드 바로 호출해줌
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "world");
        // hello 라는 이름을 viewResolver 에 넘김
        // Thymeleaf 가 'hello'.html 이라는 파일을
        // resources/templates 에서 찾아서
        // 랜더링을 하고 client 에게 웹페이지를 return 해줌.
        return "hello";
    }

    @GetMapping("vladimir")
    public String helloNexon(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);

        return "vladimir";
    }

    @GetMapping("hello-khazix")
    @ResponseBody
    public String helloKhazix(@RequestParam(value = "kkk", required = true) String name) {
        return "hello " + name;
    }

    @GetMapping("hello-person")
    @ResponseBody
    public Person personAPI(@RequestParam("name") String name){
        Person p1 = new Person();
        p1.setName(name);
        return p1;
    }

    static class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
