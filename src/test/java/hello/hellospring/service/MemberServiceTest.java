package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();

    @Test
    void 회원가입() {
        // Test는 한글로 이름을 적어도 상관없음.

        // given : 뭔가가 주어졌을 때
        Member member = new Member();
        member.setName("hello");

        // when : 이걸 실행했을 때
        Long saveId = memberService.join(member);

        // then : 결과가 이게 나와야 함
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}