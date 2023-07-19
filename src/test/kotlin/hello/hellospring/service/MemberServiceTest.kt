package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.dto.MemberDto
import hello.hellospring.repository.MemoryMemberRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired

class MemberServiceTest(
    private val memberService: MemberService,
    private val memberRepository: MemoryMemberRepository
) {

    @BeforeEach
    fun beforeEach() {
        val memberService = MemberService(memberRepository)
    }

    @AfterEach
    fun afterEach() {
        memberRepository.clearStore()
    }

    @Test
    @DisplayName("회원가입")
    fun join() {
        val member = MemberDto("spring", null)

        memberService.join(member)

        val results = memberRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("spring")
        assertThat(results[0].id).isNull()
    }

    @Test
    fun validateDuplicateMember() {
    }

    @Test
    fun findMembers() {
    }

    @Test
    fun findOne() {
    }
}