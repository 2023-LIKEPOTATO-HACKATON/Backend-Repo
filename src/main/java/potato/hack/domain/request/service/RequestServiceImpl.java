package potato.hack.domain.request.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import potato.hack.domain.credit.entity.Credit;
import potato.hack.domain.credit.entity.CreditStatus;
import potato.hack.domain.credit.repository.CreditRepository;
import potato.hack.domain.member.entity.Member;
import potato.hack.domain.member.repository.MemberRepository;
import potato.hack.domain.request.dto.BoardRequestDTO;
import potato.hack.domain.request.dto.BoardRequestUpdateDTO;
import potato.hack.domain.request.dto.BoardResponseByAdminDTO;
import potato.hack.domain.request.dto.BoardResponseByUserDTO;
import potato.hack.domain.request.dto.BoardResponseDTO;
import potato.hack.domain.request.entity.RequestBoard;
import potato.hack.domain.request.entity.RequestStatus;
import potato.hack.domain.request.repository.RequestRepository;
import potato.hack.global.s3.ImageDTO;
import potato.hack.global.s3.S3Util;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final int PLUS_VALUE = 500;
    private final RequestRepository requestRepository;
    private final MemberRepository memberRepository;
    private final CreditRepository creditRepository;
    private final S3Util s3Util;

    /**
     * 사용자가 영상파일을 올려 관리자에게 인증 요청
     *
     * @param requestDTO
     * @return
     */
    @Override
    public String createRequest(BoardRequestDTO requestDTO) {
        MultipartFile videoFile = requestDTO.getVideoFile();
        ImageDTO imageDTO = s3Util.uploadVideoFileToS3(videoFile);

        Member member = memberRepository.findById(requestDTO.getMid()).orElseThrow(
                () -> new IllegalArgumentException("올바르지 않은 mid")
        );

        RequestBoard entity = requestDTO.toEntity(imageDTO, member);
        requestRepository.save(entity);

        return "success";
    }

    /**
     * 관리자자가 사용자의 인증요청 영상을 보고 거절 또는 수락
     *
     * @param rno
     * @param updateDTO
     * @return
     */
    @Override
    public String updateRequest(Long rno, BoardRequestUpdateDTO updateDTO) {
        RequestBoard requestBoard = requestRepository.findById(rno).orElseThrow(
                () -> new IllegalArgumentException("올바르지 않은 rno")
        );

        Member member = requestBoard.getMember();

        requestBoard.updateStatus(updateDTO);
        RequestStatus status = updateDTO.getRequestStatus();

        if (status == RequestStatus.ACCEPT) {
            Credit credit = createCredit(requestBoard);
            creditRepository.save(credit);
            member.changeCredit(true, PLUS_VALUE);
            memberRepository.save(member);
        }

        requestRepository.save(requestBoard);
        return "success";
    }

    /**
     * 단건조회
     *
     * @param rno
     * @return
     */
    @Override
    public BoardResponseDTO getOne(Long rno) {
        RequestBoard requestBoard = requestRepository.findById(rno)
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 rno"));

        return requestBoard.toResponseDTO();
    }

    /**
     * 유저가 보는 인증요청 리스트
     *
     * @param mid
     * @return
     */
    @Override
    public List<BoardResponseByUserDTO> getListByUser(String mid) {
        List<RequestBoard> listByUser = requestRepository.findListByUser(mid);
        return listByUser.stream()
                .map(RequestBoard::toResponseByUserDTO)
                .collect(Collectors.toList());
    }

    /**
     * 관리자가 보는 인증요청 리스트
     *
     * @return
     */
    @Override
    public List<BoardResponseByAdminDTO> getListByAdmin() {
        List<RequestBoard> listByAdmin = requestRepository.findAll();
        return listByAdmin.stream()
                .map(RequestBoard::toResponseByAdminDTO)
                .collect(Collectors.toList());
    }

    private Credit createCredit(RequestBoard requestBoard) {
        return Credit.builder()
                .credit_status(CreditStatus.ACCRUAL)
                .credit_value(PLUS_VALUE)
                .cause_id(requestBoard.getRno())
                .description("적립")
                .member(requestBoard.getMember())
                .build();
    }
}
