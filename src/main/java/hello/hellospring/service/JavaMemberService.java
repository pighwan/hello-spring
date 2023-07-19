package hello.hellospring.service;

import hello.hellospring.domain.JavaMember;
import hello.hellospring.repository.JavaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
//public class JavaMemberService {
//// ctrl + shift + t 를 누르면 테스트 뼈대가 자동 생성.
//    private final JavaMemberRepository javaMemberRepository;
//
//    @Autowired
//    public JavaMemberService(JavaMemberRepository memberRepository) {       // dependency Injection(DI). 의존관계 주입.
//        this.javaMemberRepository = memberRepository;
//    }
//
//    /**
//     * 회원 가입
//     */
//    public Long join(JavaMember member) {
//        // 같은 이름이 있는 중복 회원 x
////        Optional<Member> result = memberRepository.findByName(member.getName());
////        result.ifPresent(m -> {     // ifPresent : 값이 있으면 로직이 동작.(Optional이기에 가능)
////            throw new IllegalStateException("이미 존재하는 회원입니다.");
////        });
//        // 위의 것을 줄이면 아래
////        memberRepository.findByName(member.getName())
////                .ifPresent(m -> {
////                    throw new IllegalStateException("이미 존재하는 회원입니다.");
////                });
//        // 위의 것을 리팩토링하면 아래
//        validateDuplicateMember(member);    // 중복 회원 검증
//        javaMemberRepository.save(member);
//        return member.getId();
//    }
//
//    private void validateDuplicateMember(JavaMember member) {
//        javaMemberRepository.findByName(member.getName())
//                        .ifPresent(m -> {
//                            throw new IllegalStateException("이미 존재하는 회원입니다.");
//                        });
//    }
//    // 서비스는 비지니스를 처리하는게 서비스의 롤이므로 서비스는 비지니스에 의존적으로 설계, 용어 또한 비지니스에 맞게 선택
//    // 리포지토리는 기계적으로 개발스럽게 용어를 선택
//
//    /**
//     * 전체 회원 조회
//     */
//    public List<JavaMember> findMembers() {
//        return javaMemberRepository.findAll();
//    }
//
//    public Optional<JavaMember> findOne(Long memberId) {
//        return javaMemberRepository.findById(memberId);
//    }
//
//}
//----------------------------------------------------------------------------------------------------------
// 자바 코드로 직접 스프링 빈 등록하기

@Transactional
public class JavaMemberService {
    // ctrl + shift + t 를 누르면 테스트 뼈대가 자동 생성.


//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // MemberService와 MemberServiceTest의 new MemoryMemberRepository()는 서로 다른 친구임.
    // 같은 인스턴스를 쓰게 바꾸는 작업이 아래. (DI)

    private final JavaMemberRepository memberRepository;

    public JavaMemberService(JavaMemberRepository memberRepository) {       // dependency Injection(DI). 의존관계 주입.
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */

    public Long join(JavaMember member) {
        // 같은 이름이 있는 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        // 위의 것을 줄이면 아래
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
        // 위의 것을 리팩토링하면 아래
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(JavaMember member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    // 서비스는 비지니스를 처리하는게 서비스의 롤이므로 서비스는 비지니스에 의존적으로 설계, 용어 또한 비지니스에 맞게 선택
    // 리포지토리는 기계적으로 개발스럽게 용어를 선택

    /**
     * 전체 회원 조회
     */
    public List<JavaMember> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<JavaMember> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
