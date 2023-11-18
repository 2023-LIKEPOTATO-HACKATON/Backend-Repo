package potato.hack.domain.credit.service;

import potato.hack.domain.credit.entity.Credit;
import potato.hack.global.pageDTO.PageResponseDTO;

public interface CreditService {

    PageResponseDTO<Credit> getCreditList();


}
