package potato.hack.domain.gift.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequestDTO {
    private Long gno;
    private String mid;
}
