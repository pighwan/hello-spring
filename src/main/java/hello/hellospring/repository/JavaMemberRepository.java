package hello.hellospring.repository;

import hello.hellospring.domain.JavaMember;

import java.util.List;
import java.util.Optional;

public interface JavaMemberRepository {
    JavaMember save(JavaMember member);
    Optional<JavaMember> findById(Long id);         // Optional <- Java8에 들어간 기능.
    Optional<JavaMember> findByName(String name);   // findByName이나 findById를 했을 때 null일 수 있음
    List<JavaMember> findAll();                     // Optional로 감싸는 것이 null을 처리하는 방법이라 생각하면 됨
}
