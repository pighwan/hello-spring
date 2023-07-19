package hello.hellospring.repository;

// Spring Data JPA
// - 스프링부트와 JPA만 사용해도 개발 생산성이 정말 많이 증가하고, 개발해야할 코드도 확연히 줄어듭니다. 여기에 스프링 데이터 JPA를 사용하면,
//   기존의 한계를 넘어 마치 마법처럼 리포지토리에 구현 클래스 없이 인터페이스 만으로 개발을 완료할 수 있습니다. 그리고 반복 개발해온 기본
//   CRUD 기능도 스프링 스프링 데이터 JPA가 모두 제공합니다. 스프링 부트와 JPA라는 기반 위에 스프링 데이터 JPA라는 환상적인 프레임워크를
//   더하면 개발이 정말 즐거워집니다. 지금까지 조금이라도 단순하고 반복이라 생각했던 개발 코드들이 확연하게 줄어듭니다. 따라서 개발자는 핵심
//   비지니스 로직을 개발하는데 집중할 수 있습니다. 실무에서 관계형 데이터베이스를 사용한다면 스프링 데이터 JPA는 이제 선택이 아니라 필수입니다.

// -주의: 스프링 데이터 JPA는 JPA를 편리하게 사용하도록 도와주는 기술입니다. 따라서 JPA를 먼저 학습한 후에 스프링 데이터 JPA를 학습해야 합니다.

import hello.hellospring.domain.JavaMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<JavaMember, Long>, JavaMemberRepository {
// 인터페이스가 인터페이스를 받을때는 extends라고 함. implements x
// 인터페이스는 다중 상속 가능.

    // JPQL  select m from JavaMember m where m.name = ?
    @Override
    Optional<JavaMember> findByName(String name);

}

// Spring Data JPA 제공 기능
// - 인터페이스를 통한 기본적인 CRUD
// - 'findByName()', 'findByEmail()' 처럼 메서드 이름만으로 조회 기능 제공
// - 페이징 기능 자동 제공
// 참고: 실무에서는 JPA와 스프링 데이터 JPA를 기본으로 사용하고, 복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용하면 된다.
//      Querydsl을 사용하면 쿼리도 자바 코드로 안전하게 작성할 수 있고, 동적 쿼리도 편리하게 작성할 수 있다. 이 조합으로 해결
//      하기 어려운 쿼리는 JPA가 제공하는 네이티브 쿼리를 사용하거나, 앞서 학습한 스프링 JdbcTemplate를 사용하면 된다.