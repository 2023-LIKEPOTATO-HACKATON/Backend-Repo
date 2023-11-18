package potato.hack.domain.gift.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import potato.hack.domain.gift.dto.GiftResponseDTO;
import potato.hack.domain.member.entity.Member;
import potato.hack.global.BaseTimeEntity;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "gift")
public class Gift extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    @Column(name = "gift_name")
    private String gift_name;

    private String brand_name;

    @Column(name = "gift_price")
    private double price; // 원가

    @Column(name = "credit_price")
    private double credit; // 크레딧 가격

    private String image_url;

    private String object_path;

    private boolean is_sold;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid", nullable = false)
    private Member member;

    public void setIs_sold(boolean is_sold) {
        this.is_sold = is_sold;
    }

    public GiftResponseDTO toDTO() {
        return GiftResponseDTO.builder()
                .gno(this.gno)
                .gift_name(this.gift_name)
                .brand_name(this.brand_name)
                .price(this.price)
                .credit(this.credit)
                .discount_rate(Math.round(100 - (this.credit / this.price * 100)))
                .image_url(this.image_url)
                .build();
    }
}
