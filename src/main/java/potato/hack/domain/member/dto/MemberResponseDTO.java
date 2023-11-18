package potato.hack.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

/**
 * 클라이언트에게 전달할 사용자 정보를 담는다.
 * @author 이동헌
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDTO {

    @NonNull
    private String mid;
    @NonNull
    private String name;
    @NonNull
    private String phone;
    @NonNull
    private String role;
}
