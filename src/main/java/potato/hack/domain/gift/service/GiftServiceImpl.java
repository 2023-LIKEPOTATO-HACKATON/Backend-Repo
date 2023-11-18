package potato.hack.domain.gift.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import potato.hack.domain.gift.dto.GiftRequestDTO;
import potato.hack.domain.gift.dto.GiftResponseDTO;
import potato.hack.domain.gift.entity.Gift;
import potato.hack.domain.gift.repository.GiftRepository;
import potato.hack.domain.member.entity.Member;
import potato.hack.domain.member.repository.MemberRepository;
import potato.hack.global.pageDTO.PageRequestDTO;
import potato.hack.global.pageDTO.PageResponseDTO;
import potato.hack.global.s3.ImageDTO;
import potato.hack.global.s3.S3Util;

@RequiredArgsConstructor
@Service
public class GiftServiceImpl implements GiftService {

    private final GiftRepository giftRepository;
    private final MemberRepository memberRepository;
    private final S3Util s3Util;

    @Override
    public String createGift(GiftRequestDTO requestDTO) {
        MultipartFile imageFile = requestDTO.getImageFile();
        ImageDTO imageDTO = s3Util.uploadFileToS3(imageFile, "image");

        Member member = memberRepository.findById(requestDTO.getMid()).orElseThrow(
                () -> new IllegalArgumentException("올바르지 않은 mid")
        );

        Gift gift = requestDTO.toEntity(imageDTO, member);
        giftRepository.save(gift);

        return "success";
    }

    @Override
    public String deleteGift(Long gno) {
        Gift gift = giftRepository.findById(gno).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않은 gift"));

        s3Util.deleteFileFromS3(gift.getObject_path());
        giftRepository.delete(gift);
        return "success";
    }

    @Override
    public GiftResponseDTO getGiftOne(Long gno) throws IllegalAccessException {
        Gift gift = giftRepository.findById(gno)
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 gno"));

        if (gift.is_sold()) {
            throw new IllegalAccessException("품절된 상품입니다.");
        }

        GiftResponseDTO dto = gift.toDTO();
        return dto;
    }

    @Override
    public PageResponseDTO<GiftResponseDTO> getGiftList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable();
        Page<GiftResponseDTO> page = giftRepository.getPage(pageable);

        return PageResponseDTO.<GiftResponseDTO>withAll()
                .pageRequestDTO(requestDTO)
                .dtoList(page.getContent())
                .total((int) page.getTotalElements())
                .build();
    }


}
