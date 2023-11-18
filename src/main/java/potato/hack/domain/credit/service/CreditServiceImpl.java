package potato.hack.domain.credit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import potato.hack.domain.credit.dto.CreditResponseDTO;
import potato.hack.domain.credit.entity.Credit;
import potato.hack.domain.credit.repository.CreditRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;

    /**
     * Credit 엔티티 목록을 dto로 변환하여 Controller에게 반환한다.
     * @param mid
     * @return
     */
    @Override
    public List<CreditResponseDTO> getCreditList(String mid) {
        List<Credit> creditList = creditRepository.findCreditListByMid(mid);

        return creditList.stream()
                .map(this::entityDTO)
                .collect(Collectors.toList());
    }

    /**
     * Credit Entity를 전달받아 DTO로 변환한다.
     * @param credit
     * @return CreditResponseDTO
     */
    private CreditResponseDTO entityDTO(Credit credit){

        return CreditResponseDTO.builder()
                .cno(credit.getCno())
                .credit_value(credit.getCredit_value())
                .cause_description(credit.getDescription())
                .credit_status(credit.getCredit_status().toString())
                .build();
    }
}
