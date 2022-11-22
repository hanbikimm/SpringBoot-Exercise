package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// SpringDataJpaRepository가 JpaRepository를 extends 하면,
// SpringDataJpaRepository가 구현체를 자동으로 만들어 Bean에 등록을 한다.
// 우리는 이걸 그냥 가져다 쓰기만 하면 된다.
// 기본적인 CRUD 등 기능들이 기본적으로 내장이 되어있어 생략 가능. 하지만 findByName은 불가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // findBy + 의 규칙을 통해 메서드 이름만으로
    // select m from Member m where m.name = ? 이라는 JPQL이 자동으로 생성됨.
    @Override
    Optional<Member> findByName(String name);
}
