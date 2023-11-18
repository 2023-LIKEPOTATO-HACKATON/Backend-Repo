package potato.hack.domain.gift.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import potato.hack.domain.gift.entity.Gift;
import potato.hack.domain.member.entity.Member;
import potato.hack.domain.member.repository.MemberRepository;


@SpringBootTest
class GiftRepositoryTest {

    @Autowired
    GiftRepository giftRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @Rollback(value = false)
    void create_gift() {
        Member member = Member.builder()
                .mid("dhl")
                .name("이동헌")
                .phone("01012341234")
                .credit_total(2350)
                .password(passwordEncoder.encode("1111"))
                .build();

        memberRepository.save(member);

        for (int i = 0; i < 20; i++) {
            Gift gift = Gift.builder()
                    .gift_name("gift_name" + i)
                    .brand_name("brand_name" + i)
                    .price(5000)
                    .credit(4000)
                    .is_sold(false)
                    .object_path("../objectPath")
                    .image_url("../url")
                    .member(member)
                    .build();

            giftRepository.save(gift);
        }
    }
}