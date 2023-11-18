package potato.hack.domain.gift.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import potato.hack.domain.gift.dto.GiftRequestDTO;
import potato.hack.domain.gift.dto.GiftResponseDTO;
import potato.hack.domain.gift.dto.PurchaseRequestDTO;
import potato.hack.domain.gift.service.GiftService;
import potato.hack.global.pageDTO.PageRequestDTO;
import potato.hack.global.pageDTO.PageResponseDTO;

@RestController
@RequestMapping("/gift")
@RequiredArgsConstructor
public class GiftController {

    private final GiftService giftService;

    @PostMapping("/create")
    public String createGift(GiftRequestDTO giftRequestDTO) {
        return giftService.createGift(giftRequestDTO);
    }

    @DeleteMapping("/delete/{gno}")
    public String deleteGift(@PathVariable("gno") Long gno) {
        return giftService.deleteGift(gno);
    }

    @PostMapping("/purchase")
    public String purchaseGift(PurchaseRequestDTO purchaseRequestDTO) {
        return giftService.purchaseGift(purchaseRequestDTO);
    }

    @GetMapping("/{gno}")
    public GiftResponseDTO getOne(@PathVariable("gno") Long gno) throws IllegalAccessException {
        return giftService.getGiftOne(gno);
    }

    @GetMapping("/list")
    public PageResponseDTO<GiftResponseDTO> getList(PageRequestDTO requestDTO) {
        return giftService.getGiftList(requestDTO);
    }
}
