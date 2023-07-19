package hello.hellospring.repository

import hello.hellospring.domain.Member
import hello.hellospring.dto.MemberDto
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MemberRepository {

    fun save(member: MemberDto): Member

    fun findById(id: Long?): Optional<Member>

    fun findByName(name: String?): Optional<Member>

    fun findAll(): List<Member>
}