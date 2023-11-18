package potato.hack.domain.member.service;


import potato.hack.domain.member.dto.MemberJoinDTO;
import potato.hack.domain.member.dto.MemberResponseDTO;

public interface MemberService {

    MemberResponseDTO getStudentDetails(String mid);

    String getRoleSetByMid(String mid);

    void registerStudent(MemberJoinDTO memberJoinDTO) ;

    int getMyCreditTotal(String mid);
}
