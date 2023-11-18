package potato.hack.domain.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import potato.hack.domain.member.entity.Member;
import potato.hack.domain.request.entity.RequestBoard;
import potato.hack.domain.request.entity.RequestStatus;
import potato.hack.global.s3.ImageDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDTO {
    String mid;
    MultipartFile videoFile;

    public RequestBoard toEntity(ImageDTO imageDTO, Member member) {
        return RequestBoard.builder()
                .description("")
                .requestStatus(RequestStatus.PENDING)
                .object_path(imageDTO.getObjectPath())
                .url(imageDTO.getUrl())
                .member(member)
                .build();
    }
}
