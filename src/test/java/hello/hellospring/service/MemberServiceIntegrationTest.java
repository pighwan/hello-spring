package hello.hellospring.service;

import hello.hellospring.domain.JavaMember;
import hello.hellospring.repository.JavaMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
// 통합 테스트. 하지만 단위 테스트를 잘 만드는 것이 더 중요하다~
// 테스트를 잘 작성하는 것에 대해 고민하고 직접 작성해보는 것이 중요!
// 개발을 잘 하는 사람일수록 테스트를 꼼꼼하게 작성하고 테스트 케이스를 잘 작성함
@SpringBootTest
@Transactional  // DB에 test한 것을 롤백해줘서 반복 테스트를 가능하게 해줌(테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다. 다음 테스트에 영향을 주지 않는다)
class MemberServiceIntegrationTest {

    @Autowired
    JavaMemberService memberService;
    @Autowired
    JavaMemberRepository memberRepository;

    @Test
    void 회원가입() {
        // Test는 한글로 이름을 적어도 상관없음.

        // given : 뭔가가 주어졌을 때
        JavaMember member = new JavaMember();
        member.setName("spring");

        // when : 이걸 실행했을 때
        Long saveId = memberService.join(member);

        // then : 결과가 이게 나와야 함
        JavaMember findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        // given
        JavaMember member1 = new JavaMember();
        member1.setName("spring");

        JavaMember member2 = new JavaMember();
        member2.setName("spring");

        // when
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
//        -----------------------------------------------------------------------------------
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        -----------------------------------------------------------------------------------
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}