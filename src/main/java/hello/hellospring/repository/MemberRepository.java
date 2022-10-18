package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);         // Optional <- Java8에 들어간 기능.
    Optional<Member> findById(String name);     //          findByName이나 findById를 했을 때 null일 수 있음
    List<Member> findAll();                     //          null을 처리하는 방법이라 생각하면 됨
}
