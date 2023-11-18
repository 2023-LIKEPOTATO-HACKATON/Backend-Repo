    package potato.hack.domain.credit.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import potato.hack.domain.credit.entity.Credit;

    import java.util.List;

    public interface CreditRepository extends JpaRepository<Credit, Long> {

        /**
         * 사용자의 ID로 나의 크레딧 목록 정보를 검색한다.
         * @param mid
         * @return Credit 객체의 List
         */
        @Query("SELECT c FROM Credit c WHERE c.member.mid = :mid")
        List<Credit> findCreditListByMid(@Param("mid") String mid);

    }
