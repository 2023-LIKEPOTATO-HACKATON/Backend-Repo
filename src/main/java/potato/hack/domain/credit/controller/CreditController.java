package potato.hack.domain.credit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import potato.hack.domain.credit.service.CreditService;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    @GetMapping("/list")
    public List<PageResponseDTO> getCreditList() {

        return creditService.getCreditList();
    }



}
