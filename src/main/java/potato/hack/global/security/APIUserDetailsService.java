package potato.hack.global.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import potato.hack.domain.member.entity.Member;
import potato.hack.domain.member.repository.MemberRepository;
import potato.hack.global.security.dto.APIUserDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class APIUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> result = memberRepository.findById(username);

        Member member = result.orElseThrow(() -> new UsernameNotFoundException("해당 아이디는 없는 사용자입니다."));

        APIUserDTO dto =
                new APIUserDTO(
                        member.getMid(),
                        member.getPassword(),
                        member.getRoleSet()
                                .stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                                .collect(Collectors.toList())
                );

        log.info(dto);

        return dto;
    }
}
