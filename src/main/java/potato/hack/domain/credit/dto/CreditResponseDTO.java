package potato.hack.domain.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 이동헌
 * 나의 Crediit 정보를 반환하는 DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditResponseDTO {

    private Long cno;
    private int credit_value; //크레딧 값
    private String credit_status; //적립인지 차감인지에 대한 상태
    private String cause_description; //크레딧 변동 이유
}
