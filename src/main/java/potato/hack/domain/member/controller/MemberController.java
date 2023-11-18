package potato.hack.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import potato.hack.domain.member.dto.MemberJoinDTO;
import potato.hack.domain.member.dto.MemberResponseDTO;
import potato.hack.domain.member.service.MemberService;

import javax.validation.Valid;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> joinNewUser(@Valid @RequestBody MemberJoinDTO memberJoinDTO ){
        memberService.registerStudent(memberJoinDTO);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PreAuthorize("authentication.principal.username == #mid")
    @GetMapping("/my/{mid}")
    public MemberResponseDTO getStudentInfo(@PathVariable String mid) {

        return memberService.getStudentDetails(mid);
    }

    @PreAuthorize("authentication.principal.username == #mid")
    @GetMapping("/my/Total/{mid}")
    public Map<String, Integer> getMyCreditTotal(@PathVariable String mid) {

        int myCreditTotal = memberService.getMyCreditTotal(mid);
        return Map.of("amount", myCreditTotal);
    }

}
