package potato.hack.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 사용자 권한 정보
 * @author 이동헌
 */
@Getter
@RequiredArgsConstructor
public enum MemberRole {

    STUDENT("ROLE_STUDENT"),
    ADMIN("ROLE_ADMIN");

    private String value;

    MemberRole(String value) {
        this.value = value;
    }

}
