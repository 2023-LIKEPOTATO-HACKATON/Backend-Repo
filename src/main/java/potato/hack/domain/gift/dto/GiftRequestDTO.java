package potato.hack.domain.gift.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import potato.hack.domain.gift.entity.Gift;
import potato.hack.domain.member.entity.Member;
import potato.hack.global.s3.ImageDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiftRequestDTO {

    private String mid;

    private String gift_name;

    private String brand_name;

    private int price; // 원가

    private int credit; // 크레딧 가격

    private MultipartFile imageFile;

    public Gift toEntity(ImageDTO imageDTO, Member member) {
        return Gift.builder()
                .gift_name(this.getGift_name())
                .brand_name(this.getBrand_name())
                .price(this.getPrice())
                .credit(this.getCredit())
                .image_url(imageDTO.getUrl())
                .object_path(imageDTO.getObjectPath())
                .is_sold(false)
                .member(member)
                .build();
    }
}
