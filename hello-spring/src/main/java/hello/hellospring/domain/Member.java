package hello.hellospring.domain;

import javax.persistence.*;

// JPA ORM JPA가 관리하는 Entity
@Entity
public class Member {

    // DB가 자동으로 생성: IDENTITY
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
