    package potato.hack.domain.credit.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import potato.hack.domain.credit.entity.Credit;

    import java.util.List;

    public interface CreditRepository extends JpaRepository<Credit, Long> {

        @Query("SELECT c FROM Credit c WHERE c.member.mid = :mid")
        List<Credit> findCreditListByMid(@Param("mid") String mid);

    }
