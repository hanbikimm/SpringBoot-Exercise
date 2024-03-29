package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정 정보 어노테이션
@Configuration
// Configuration에 의해 싱글톤 보장
public class AppConfig {
    // 모든 역할이 드러나게끔 작성해야함
    // appConfig로 인해 사용영역과 구성영역이 분리되었다.

    //스프링 컨테이너에 등록
    //@Bean(name="") 으로 메서드명이 아닌 다른 이름을 사용할 수 있음
    //Bean의 이름은 항상 다른 이름. 이름 중복 시 빈이 무시되거나 덮어버리는 오류 발생
    @Bean
    // 생성자 주입, 어떤 구현 객체를 주입할지 결정
    public MemberService memberService(){
        System.out.println("Call AppConfig.memeberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
