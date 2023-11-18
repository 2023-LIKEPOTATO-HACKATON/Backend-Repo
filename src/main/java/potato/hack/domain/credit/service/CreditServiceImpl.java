package potato.hack.domain.credit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import potato.hack.domain.credit.entity.Credit;
import potato.hack.global.pageDTO.PageResponseDTO;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class CreditServiceImpl implements CreditService {
    @Override
    public PageResponseDTO<Credit> getCreditList() {

        return null;
    }
}
