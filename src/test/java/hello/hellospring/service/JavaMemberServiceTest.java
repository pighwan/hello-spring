package hello.hellospring.service;

import hello.hellospring.domain.JavaMember;
import hello.hellospring.repository.JavaMemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JavaMemberServiceTest {   // MemberService에서 ctrl + shift + t 눌러서 같은 위치로 바로 생성가능.

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    JavaMemberService memberService;
    JavaMemoryMemberRepository memberRepository;

    @BeforeEach // @BeforeEach를 통해 동작하기 전에 넣어줌.
    public void beforeEach() {
        memberRepository = new JavaMemoryMemberRepository();
        memberService = new JavaMemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("회원가입")
    void join() {
        // Test는 한글로 이름을 적어도 상관없음. 근데 어른들이 싫어하니깐 그냥 @DisplayName 이용하장.
        // Test를 처음 할때는 아래의 패턴을 권장.

        // given : 뭔가가 주어졌을 때
        JavaMember member = new JavaMember();
        member.setName("spring");

        // when : 이걸 실행했을 때
        Long saveId = memberService.join(member);

        // then : 결과가 이게 나와야 함(검증)
        JavaMember findMember = memberService.findOne(saveId).get();    // ctrl + alt + v 로 이름 맹글어주기 가능.
//        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName()); // alt + enter 눌러서 static import 추가
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 테스트는 정상 플로우도 중요하지만 예외 플로우가 훨씬더 중요하다!! 위의 테스트는 반쪽짜리 테스트임.
    // join의 핵심은 저장 기능이 되는 것도 중요하지만, 중복회원검증 로직을 잘 타서 예외가 터뜨려지는 것도 확인해야함.
    @Test
    public void 중복_회원_예외() {
        // given
        JavaMember member1 = new JavaMember();
        member1.setName("spring");

        JavaMember member2 = new JavaMember();  // shift + F6으로 해당 메서드 이름 동시에 바꿔주기 가능.
        member2.setName("spring");

        // when
        memberService.join(member1);
        // 예외 터뜨리는 로직
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
//        -----------------------------------------------------------------------------------
        // 저것 때문에 try~catch 넣는게 싫으니까 assertThrow 기능 이용.
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        -----------------------------------------------------------------------------------
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}