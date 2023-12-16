package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query methods if needed
}
