package potato.hack.domain.credit.service;

import potato.hack.domain.credit.dto.CreditResponseDTO;

import java.util.List;

public interface CreditService {

    List<CreditResponseDTO> getCreditList(String mid);

}
