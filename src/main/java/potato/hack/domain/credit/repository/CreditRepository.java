package potato.hack.domain.credit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import potato.hack.domain.credit.entity.Credit;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
