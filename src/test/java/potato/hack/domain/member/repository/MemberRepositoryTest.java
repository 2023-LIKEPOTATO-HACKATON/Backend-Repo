package potato.hack.domain.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import potato.hack.domain.member.entity.Member;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @Rollback(value = false)
    void create_member() {
        Member jyp = Member.builder()
                .mid("dhl")
                .name("이동헌")
                .phone("01012341234")
                .credit_total(2350)
                .password(passwordEncoder.encode("1111"))
                .build();

        memberRepository.save(jyp);
    }
}