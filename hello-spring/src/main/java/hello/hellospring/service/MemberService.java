package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


// 과거에는 Null을 직접 처리했지만, 요즘엔 Null을 Optional로 한번 감싸서 사용한다.
// optionl 안에 멤버 객체가 있는 것. 그 덕분에 ifPresent 같은 매서드를 사용할 수 있다.
// optional 바로 반환하는 것은 좋지 않다.
// 서비스는 비즈니스에 가깝고, 비즈니스 용어로 사용. 레포는 단순한 개발스러운 용어 사용

// command + option + v : 반환가능한 형태로 변형하는 단축키
// Optional<Member> result = memberRepository.findByName(member.getName());

// 로직에 Ctrl + t 하면 리팩토링 관련 단축키로, 로직을 외부 메서드로 뺄 수 있다.

// command + shift + t : 클래스 선택하면 자동으로 테스트 파일 기초를 생성하는 단축키

// command + n : code generator 단축키 ex) constructor, getter, setter, toString

// 스프링 컨테이너에 등록하는 어노테이션
//@Service

//JPA는 모든 데이터 변경이 트랜잭션 안에서 실행되어야 한다.
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // 외부에서 레포를 넣어주는 것. 이렇게 하면 테스트 시와 같은 레포를 사용한다.
    // 스프링 컨테이너에서 멤버 레포를 가져와 연결해준다.
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member){

        // 중복 이름 허용 불가
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    // 중복회원 검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                } );
    }

    // 전체 회원 조회
    public List<Member> findMembers(){

        return memberRepository.findAll();
    }

    // 특정 회원 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
