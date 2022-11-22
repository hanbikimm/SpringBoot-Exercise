package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// @Controller를 이용하면 스프링 컨테이너에 MemberController 라는 객체가 생성되고, 스프링에서 관리한다.
// = 스프링 빈에 등록되고 관리된다.
@Controller
public class MemberController {

    // 서비스는 한번만 불러서 사용하면 되기에 스프링 컨테이너에 등록해서 관리하도록 하면 된다.
    private final MemberService memberService;

    // 스프링 컨테이너에서 멤버 서비스를 가져와 연결해준다.
    // Dependency injection 관계성 주입
    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
        // AOP 적용으로 인한 프록시 서비스 호출 확인하는 방법
        System.out.println("memberService = " + memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm(){
        return"members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        //System.out.println(member.getName());

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
    }
}
