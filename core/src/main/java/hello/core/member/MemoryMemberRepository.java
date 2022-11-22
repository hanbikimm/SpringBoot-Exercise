package hello.core.member;

import java.util.HashMap;
import java.util.Map;

// option + enter
public class MemoryMemberRepository implements MemberRepository{

    // 여러군데에서 동시에 접근하면 동시성 이슈가 있을 수 있기에 실무에서는 컨커넌트 해시맵을 사용해야 한다.
    // 해시맵은 간단한 개발용
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
