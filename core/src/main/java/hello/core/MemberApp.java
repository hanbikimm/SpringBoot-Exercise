package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// command + option + v: 실행문을 객체에 담도록 하는 단축키
public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //AppConfig의 정보를 스프링 컨테이너에서 다 관리해준다.
        // 이제는 직접 찾아오는 것이 아니라 컨테이너로부터 가져온다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        // soutv: 위에 프린트문 복사하고, 변수 변경 한번에
        System.out.println("findMember = " + findMember.getName());


    }
}
