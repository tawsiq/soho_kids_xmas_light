package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.Product;
import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.ProductService;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
