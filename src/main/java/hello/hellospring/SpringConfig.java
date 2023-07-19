package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.JavaMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

//    DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
// ------------------------------------------------------------------------------------
//    private EntityManager em;
//
//    public SpringConfig(EntityManager em) {     // alt + insert => Constructor
//        this.em = em;
//    }

    private final JavaMemberRepository memberRepository;

    @Autowired
    public SpringConfig(JavaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public JavaMemberService memberService() {
        return new JavaMemberService(memberRepository);
    }

//    @Bean
//    public JavaMemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }


}
