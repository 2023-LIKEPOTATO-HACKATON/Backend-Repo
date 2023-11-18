package potato.hack.domain.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditResponseDTO {

    private Long cno;
    private int credit_value;
    private boolean credit_status;
    private String cause_description;
}
