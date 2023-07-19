package hello.hellospring.repository;

import hello.hellospring.domain.JavaMember;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 회원 repository 테스트 케이스 작성
// - 개발한 기능을 실행해서 테스트 할 때 자바의 main 메서드를 통해서 실행하거나, 웹 애플리케이션의 컨트롤러를 통해
//   서 해당 기능을 실행한다. 이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복 실행하기 어렵고 여러 테스트를 한번
//   에 실행하기 어렵다는 단점이 있다. 자바는 JUint이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.

class JavaMemoryMemberRepositoryTest {

    JavaMemoryMemberRepository repository = new JavaMemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public void save() {
        JavaMember member = new JavaMember();
        member.setName("spring");

        repository.save(member);

        JavaMember result = repository.findById(member.getId()).get();
//        System.out.println("result = "+(result == member));
//        Assertions.assertEquals(member, result);
//        Assertions.assertEquals(member, null);        // Actual(실제값)이 null이라며 오류가 뜸.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        JavaMember member1 = new JavaMember();
        member1.setName("spring1");
        repository.save(member1);

        JavaMember member2 = new JavaMember();
        member2.setName("spring2");
        repository.save(member2);

        JavaMember result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        JavaMember member1 = new JavaMember();
        member1.setName("spring1");
        repository.save(member1);

        JavaMember member2 = new JavaMember();
        member2.setName("spring2");
        repository.save(member2);

        List<JavaMember> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
