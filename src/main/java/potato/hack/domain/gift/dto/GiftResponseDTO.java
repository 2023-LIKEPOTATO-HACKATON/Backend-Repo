package potato.hack.domain.gift.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiftResponseDTO {
    private Long gno;
    private String gift_name;
    private String brand_name;
    private double price;
    private double credit;
    private double discount_rate;
    private String image_url;
}
