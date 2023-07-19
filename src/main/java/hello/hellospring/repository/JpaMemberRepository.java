package hello.hellospring.repository;

import hello.hellospring.domain.JavaMember;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements JavaMemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {  // JPA를 쓰기 위해서는 EntityManager를 주입 받아야 한다.
        this.em = em;
    }

    public JavaMember save(JavaMember member) {
        em.persist(member);
        return member;
    }

    public Optional<JavaMember> findById(Long id) {
        JavaMember member = em.find(JavaMember.class, id);
        return Optional.ofNullable(member);
    }

    public Optional<JavaMember> findByName(String name) {
        List<JavaMember> result = em.createQuery("select m from JavaMember m where m.name = :name", JavaMember.class).setParameter("name", name).getResultList();
        return result.stream().findAny();
    }

    public List<JavaMember> findAll() {
        return em.createQuery("select m from JavaMember m", JavaMember.class).getResultList();
    }
}
