package potato.hack.domain.gift.service;

import org.springframework.stereotype.Service;
import potato.hack.domain.gift.dto.GiftRequestDTO;
import potato.hack.domain.gift.dto.GiftResponseDTO;
import potato.hack.global.pageDTO.PageRequestDTO;
import potato.hack.global.pageDTO.PageResponseDTO;

@Service
public interface GiftService {

    String createGift(GiftRequestDTO requestDTO);

    String deleteGift(Long gno);

    GiftResponseDTO getGiftOne(Long gno) throws IllegalAccessException;

    PageResponseDTO<GiftResponseDTO> getGiftList(PageRequestDTO requestDTO);
}
