package potato.hack.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
