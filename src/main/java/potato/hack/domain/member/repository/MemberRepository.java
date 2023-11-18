package potato.hack.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import potato.hack.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("SELECT m.credit_total FROM Member m WHERE m.mid = :mid")
    int getMyCreditTotal(@Param("mid") String mid);
}
