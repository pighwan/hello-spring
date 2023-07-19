package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.dto.MemberDto
import hello.hellospring.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class MemberService @Autowired constructor(
    private val memberRepository: MemberRepository,

) {
    fun join(member: MemberDto): Long? {
        validateDuplicateMember(member)
        memberRepository.save(member)
        return member.id
    }

    fun validateDuplicateMember(member: MemberDto) {
        memberRepository.findByName(member.name)
            .ifPresent{
                throw IllegalArgumentException("이미 존재하는 회원입니다.")
            }
    }

    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun findOne(memberId: Long): Optional<Member> {
        return memberRepository.findById(memberId)
    }

}