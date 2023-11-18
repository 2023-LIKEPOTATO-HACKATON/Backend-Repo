package potato.hack.domain.request.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potato.hack.domain.request.entity.RequestStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseByUserDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime regDate;
    private Long rno;
    private String description;
    private RequestStatus requestStatus;

}
