package potato.hack.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

/**
 *  회원가입 시, 사용자가 입력한 정보를 담는다.
 *  @author 이동헌
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDTO {

    @NonNull
    private String mid;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String phone;
}
