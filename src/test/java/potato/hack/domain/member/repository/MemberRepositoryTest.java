package potato.hack.domain.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import potato.hack.domain.member.entity.Member;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    void create_member() {
        Member jyp = Member.builder()
                .mid("jyp")
                .email("...@naver.com")
                .password("1111")
                .build();

        memberRepository.save(jyp);
    }
}