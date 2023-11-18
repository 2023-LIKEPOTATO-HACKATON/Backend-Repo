package potato.hack.global.exception;

/**
 * @author 이동헌
 * 파일 업로드 시, 동영상 파일이 아닌 경우 발생하는 에러
 */
public class IsNotVideoFileException extends RuntimeException {

    /**
     * 전달받은 파일이 영상 파일이 아니라면 예외를 생성한다.
     *
     */
    public IsNotVideoFileException(String fileName) {
        super("선택한 파일 (" + fileName + ") 은  동영상 파일이 아닙니다.");
    }
}
