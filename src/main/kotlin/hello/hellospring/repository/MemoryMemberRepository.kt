package hello.hellospring.repository

import hello.hellospring.domain.Member
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Repository
interface MemoryMemberRepository : MemberRepository{
    companion object {
        private val store = HashMap<Long, Member>()
        private var sequence = 0L
    }

    fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id!!] = member
        return member
    }

    override fun findById(id: Long?): Optional<Member> {
        return Optional.ofNullable(store[id])
    }

    override fun findByName(name: String?): Optional<Member> {
        return store.values.stream()
            .filter { member -> member.name == name }
            .findAny()
    }

    override fun findAll(): List<Member> {
        return ArrayList(store.values)
    }

    fun clearStore() {
        store.clear()
    }


}