package potato.hack.global.exception;

/**
 * @author 이동헌
 * 해당 ID가 이미 존재할 때 발생하는 예외.
 */
public class MidExistsException extends RuntimeException{

    /**
     * 만약 전달받은 ID가 존재한다면 예외를 반환한다.
     *
     * @param mid
     */
    public MidExistsException(String mid) {
        super("해당 ID(" + mid + ")는 이미 존재합니다.");
    }
}
