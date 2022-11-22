package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // build.gradle에서 data-jpa 라이브러리를 설정하면, Boot가 자동으로 DB와 연결하며 EntityManager를 생성한다.
    // 그렇기에 자동으로 생성된 EntityManager를 우리는 가져다 쓰면 된다.
    // dataSource도 포함되어 있어 DB와 통신도 여기서 다룬다.
    // 저장, 조회, 삭제, 수정은 쿼리를 짤 필요가 없다. PK 기반이 아닌 것은 JPQL 써야한다.
    // 주의: JPA는 항상 transaction이 있어야 한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // Ctrl + t: inline variable 하면 return문과 실행문 하나로 합쳐짐
        // JPQL 객체(entity)를 대상으로 쿼리를 날리면 이것이 sql로 바뀌어 날라간다
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
