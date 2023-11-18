package potato.hack.domain.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potato.hack.domain.request.entity.RequestStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDTO {
    private Long rno;
    private String description;
    private RequestStatus requestStatus;
    private String url;
}
