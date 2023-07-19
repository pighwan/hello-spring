package hello.hellospring.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Member(
    var name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {
    companion object {
        fun fixture(
            name: String = "호랭이",
            id: Long? = null,
        ): Member {
            return Member(
                name = name,
                id = id,
            )
        }
    }
}