package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//ctrl + R: 마지막 실행한 것을 실행해줌

// controller는 컴포넌트 스캔과 자동 의존 관계 설정 때와 똑같이 놔두면 된다.

// 자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

    // JDBC
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // JpaRepository
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }


    // 이렇게만 하면 SpringDataJpa가 자동으로 구현체를 만들어 Bean에 등록한다.
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

    // SpringDataJpa 사용하면 이것 생략 가능
//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }


//    @Bean
//    public MemberRepository memberRepository(){
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//    }
}
