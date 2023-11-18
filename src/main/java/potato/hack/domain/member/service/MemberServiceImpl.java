package potato.hack.domain.member.service;


import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import potato.hack.domain.member.dto.MemberJoinDTO;
import potato.hack.domain.member.dto.MemberResponseDTO;
import potato.hack.domain.member.entity.Member;
import potato.hack.domain.member.entity.MemberRole;
import potato.hack.domain.member.repository.MemberRepository;
import potato.hack.global.exception.MidExistsException;

import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@ToString
@Service
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * mid를 전달하여 memberRepository에서 검색한 사용자 정보를 DTO로 변환하여 return한다.
     * @author 이동헌
     * @param mid
     * @return MemberResponseDTO
     */
    public MemberResponseDTO getStudentDetails(String mid) {

        Optional<Member> result = memberRepository.findById(mid);

        Member member = result.orElseThrow();

        MemberRole role = member.getRoleSet().iterator().next();

        return MemberResponseDTO.builder()
                .mid(member.getMid())
                .name(member.getName())
                .phone(member.getPhone())
                .role(String.valueOf(role))
                .build();
    }

    /**
     * JWTUtil에서 Token 생성 시, payload에 추가할 사용자 권한 정보(role)를 조회하여 반환한다.
     * @author 이동헌
     * @param mid
     * @return role
     */
    @Override
    public String getRoleSetByMid(String mid) {
        Optional<Member> result = memberRepository.findById(mid);
        if (result.isPresent()) {
            Member member = result.get();
            String role = String.join(",",
                    member.getRoleSet().stream().map(MemberRole::getValue).collect(Collectors.toList()));
            log.info("해당 유저는 " + role + " 권한을 가지고 있습니다.");
            return role;
        } else {
                return String.valueOf(new MidExistsException(mid));
            }
        }

    /**
     * DTO로 사용자 가입 정보를 전달받아 Entity로 변환하고, memberRepository를 통해 저장한다.
     * @author 이동헌
     * @param memberJoinDTO
     */
    public void registerStudent(MemberJoinDTO memberJoinDTO) {

        String mid = memberJoinDTO.getMid();

        log.info("-----" + mid);

        boolean exists = memberRepository.existsById(mid);

        if (exists) {
            throw new MidExistsException(mid);
        }

        Member member = Member.builder()
                .mid(memberJoinDTO.getMid())
                .name(memberJoinDTO.getName())
                .phone(memberJoinDTO.getPhone())
                .password(memberJoinDTO.getPassword())
                .build();

        member.changePassword(passwordEncoder.encode(memberJoinDTO.getPassword()));

        member.addRole(MemberRole.STUDENT);

        log.info("---member--------" + member);

        memberRepository.save(member);
    }

    /**
     * mid를 전달받아 memberRepository에서 검색한 사용자 크레딧 총액 정보를 반환한다.
     * @param mid
     * @return
     */
    @Override
    public int getMyCreditTotal(String mid) {

        return memberRepository.getMyCreditTotal(mid);
    }
}
