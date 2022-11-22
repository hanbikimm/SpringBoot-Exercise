package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 이렇게 되면 인터페이스도 의존, 구현체도 의존 둘다 하기에 좋지 않은 방식
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    // AppConfig와 함께 생성자를 통해 객체가 들어가므로, 생성자 주입 방식이 된다.
    // 이제는 추상화만 의존하게 된다.입 어떤 구현객체가 주입될지는 impl은 신경쓰지 않아도 된다. 실행에 집중하면 된다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
