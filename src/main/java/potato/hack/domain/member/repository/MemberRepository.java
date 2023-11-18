package potato.hack.domain.member.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import potato.hack.domain.member.entity.Member;




public interface MemberRepository extends JpaRepository<Member, String> {

}
