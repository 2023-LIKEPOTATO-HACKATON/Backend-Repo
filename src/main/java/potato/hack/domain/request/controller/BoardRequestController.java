package potato.hack.domain.request.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import potato.hack.domain.request.dto.BoardRequestDTO;
import potato.hack.domain.request.dto.BoardRequestUpdateDTO;
import potato.hack.domain.request.dto.BoardResponseByAdminDTO;
import potato.hack.domain.request.dto.BoardResponseByUserDTO;
import potato.hack.domain.request.dto.BoardResponseDTO;
import potato.hack.domain.request.service.RequestService;

@RestController
@RequestMapping("/board-request")
@RequiredArgsConstructor
public class BoardRequestController {

    private final RequestService requestService;

    @PostMapping("/create")
    public String create(BoardRequestDTO requestDTO) {
        return requestService.createRequest(requestDTO);
    }

    @PutMapping("/update/{rno}")
    public String update(@PathVariable("rno") Long rno,
                         BoardRequestUpdateDTO updateDTO) {
        return requestService.updateRequest(rno, updateDTO);
    }

    @GetMapping("/{rno}")
    public BoardResponseDTO getListByUser(@PathVariable("rno") Long rno) {
        return requestService.getOne(rno);
    }


    @GetMapping("/list/{mid}")
    public List<BoardResponseByUserDTO> getListByUser(@PathVariable("mid") String mid) {
        return requestService.getListByUser(mid);
    }

    @GetMapping("/list")
    public List<BoardResponseByAdminDTO> getListByUser() {
        return requestService.getListByAdmin();
    }
}
