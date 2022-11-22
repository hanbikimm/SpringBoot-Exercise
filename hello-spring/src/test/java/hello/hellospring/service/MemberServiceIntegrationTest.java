package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

// DB와 스프링 컨테이너까지 연결한 통합 테스트
@SpringBootTest
// DB에 쿼리 후 commit을 해야 DB에 반영이 된다.
// 이 어노테이션을 쓰면 DB 반영 후 테스트가 끝나면 Roll Back 해서 DB를 지워준다. 덕분에 @aftereach가 필요없음
// 즉 DB를 지우는 코드를 사용하지 않고, 테스트를 계속해서 반복할 수 있다.
@Transactional
class MemberServiceIntegrationTest {

    // 테스트할 때는 필드 주입도 괜찮다. 필요한 것만 여기서 테스트하고 끝낼거니까
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {

        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);

        // try-catch 대신 이를 사용하는 것이 좋음
        //                        이 예외가 터져야함                                  이 로직을 돌릴 때
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}