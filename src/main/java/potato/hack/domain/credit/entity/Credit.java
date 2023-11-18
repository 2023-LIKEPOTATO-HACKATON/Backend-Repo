package potato.hack.domain.credit.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import potato.hack.domain.member.entity.Member;

import javax.persistence.*;

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

    private Long cause_id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid", nullable = false)
    private Member member;
}