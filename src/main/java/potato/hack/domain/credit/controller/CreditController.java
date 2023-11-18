package potato.hack.domain.credit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import potato.hack.domain.credit.dto.CreditResponseDTO;
import potato.hack.domain.credit.service.CreditService;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    /**
     * 로그인한 사용자의 크레딧 목록을 반환한다.
     * @param mid
     * @return
     */

    @PreAuthorize("authentication.principal.username == #mid")
    @GetMapping("/my/list/{mid}")
    public List<CreditResponseDTO> getCreditList(@PathVariable String mid) {

        return creditService.getCreditList(mid);
    }

}
