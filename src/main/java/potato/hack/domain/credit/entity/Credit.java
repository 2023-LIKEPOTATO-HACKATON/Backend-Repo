package potato.hack.domain.credit.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import potato.hack.domain.member.entity.Member;

import javax.persistence.*;

/**
 * @author 이동헌
 * 사용자의 크레딧 정보 엔티티
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    @Enumerated(EnumType.STRING)
    @Column(name = "credit_status")
    private CreditStatus credit_status;

    @Column(name = "credit_value")
    private int credit_value; // 감가액

    private Long cause_id; //크레딧 변동 원인 id

    private String description; //변동 사유

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid", nullable = false)
    private Member member;

}
