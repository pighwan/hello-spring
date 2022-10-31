package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    ////    [필드 주입 방식] - 별루 안씀
//    @Autowired
//    private MemberService memberService;
//----------------------------------------------------------------------------------------------------------
////    [Setter 주입 방식] - 단점 : public하게 노출이됨.
//    private MemberService memberService;
//
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
//----------------------------------------------------------------------------------------------------------
//    [생성자 주입 방식] - 요즘 권장하는 방식
//    참고 : 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다. 그리고 정형화되지 않거나, 상황
//          에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    @GetMapping("/members/new")     // 데이터를 조회할 때는 GetMapping을 사용
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")     // 데이터를 전달할 때는 PostMapping을 사용
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}