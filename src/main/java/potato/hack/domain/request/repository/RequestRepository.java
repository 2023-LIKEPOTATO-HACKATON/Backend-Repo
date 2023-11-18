package potato.hack.domain.request.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import potato.hack.domain.request.entity.RequestBoard;

public interface RequestRepository extends JpaRepository<RequestBoard, Long> {

    @Query("SELECT rb FROM RequestBoard rb WHERE rb.member.mid = :user_id")
    List<RequestBoard> findListByUser(@Param("user_id") String user_id);
}
