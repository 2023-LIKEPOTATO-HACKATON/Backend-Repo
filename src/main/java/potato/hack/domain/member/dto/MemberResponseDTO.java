package potato.hack.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

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
