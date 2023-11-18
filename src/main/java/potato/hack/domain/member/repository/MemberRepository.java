package potato.hack.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import potato.hack.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

    /**
     * 사용자 아이디를 전달해서 나의 크레딧 총액 정보를 검색한다.
     * @param mid
     * @return Credit_total
     */
    @Query("SELECT m.credit_total FROM Member m WHERE m.mid = :mid")
    int getMyCreditTotal(@Param("mid") String mid);
}
