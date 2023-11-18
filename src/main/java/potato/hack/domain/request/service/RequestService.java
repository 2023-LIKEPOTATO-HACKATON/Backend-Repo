package potato.hack.domain.request.service;

import java.util.List;
import org.springframework.stereotype.Service;
import potato.hack.domain.request.dto.BoardRequestDTO;
import potato.hack.domain.request.dto.BoardRequestUpdateDTO;
import potato.hack.domain.request.dto.BoardResponseByAdminDTO;
import potato.hack.domain.request.dto.BoardResponseByUserDTO;
import potato.hack.domain.request.dto.BoardResponseDTO;

@Service
public interface RequestService {

    String createRequest(BoardRequestDTO boardRequestDTO);

    String updateRequest(Long rno, BoardRequestUpdateDTO updateDTO);

    BoardResponseDTO getOne(Long rno);

    List<BoardResponseByUserDTO> getListByUser(String mid);

    List<BoardResponseByAdminDTO> getListByAdmin();
}
