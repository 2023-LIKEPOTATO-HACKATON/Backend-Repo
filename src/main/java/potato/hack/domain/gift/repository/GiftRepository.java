package potato.hack.domain.gift.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import potato.hack.domain.gift.dto.GiftResponseDTO;
import potato.hack.domain.gift.entity.Gift;

public interface GiftRepository extends JpaRepository<Gift, Long> {

    @Query("SELECT new potato.hack.domain.gift.dto.GiftResponseDTO("
            + "g.gno, g.gift_name, g.brand_name, g.price, g.credit, round(100 - ( g.credit / g.price * 100), 0), g.image_url) "
            + "FROM Gift g WHERE g.is_sold = false ORDER BY g.regDate DESC")
    Page<GiftResponseDTO> getPage(Pageable pageable);
}
