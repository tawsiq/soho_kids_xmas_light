package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    // Custom query method (example)
    // Retrieves all CartDetail records for a specific product ID
    //List<CartDetail> findByProductId(Long productId);
}
