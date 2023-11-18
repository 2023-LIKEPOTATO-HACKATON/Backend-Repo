package potato.hack.domain.request.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import potato.hack.domain.member.entity.Member;
import potato.hack.domain.request.dto.BoardRequestUpdateDTO;
import potato.hack.domain.request.dto.BoardResponseByAdminDTO;
import potato.hack.domain.request.dto.BoardResponseByUserDTO;
import potato.hack.domain.request.dto.BoardResponseDTO;
import potato.hack.global.BaseTimeEntity;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "request_board")
public class RequestBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String description;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    private String object_path;

    private String url;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid", nullable = false)
    private Member member;

    public void updateStatus(BoardRequestUpdateDTO updateDTO) {
        this.description = updateDTO.getDescription();
        this.requestStatus = updateDTO.getRequestStatus();
    }

    public BoardResponseByUserDTO toResponseByUserDTO() {
        return BoardResponseByUserDTO.builder()
                .regDate(getRegDate())
                .rno(rno)
                .description(description)
                .requestStatus(requestStatus)
                .build();
    }

    public BoardResponseByAdminDTO toResponseByAdminDTO() {
        return BoardResponseByAdminDTO.builder()
                .regDate(getRegDate())
                .rno(rno)
                .mid(member.getMid())
                .description(description)
                .requestStatus(requestStatus)
                .build();
    }

    public BoardResponseDTO toResponseDTO() {
        return BoardResponseDTO.builder()
                .rno(rno)
                .description(description)
                .requestStatus(requestStatus)
                .url(url)
                .build();
    }
}
