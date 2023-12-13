import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getAllProducts() {
        // In a real application, you would fetch products from a database
        // For simplicity, let's create some sample data

        List<Product> products = new ArrayList<>();

        products.add(new Product(1L, "XMAS T-Shirt 001", 30, "image1.jpg"));
        products.add(new Product(2L, "XMAS T-Shirt 002", 30, "image2.jpg"));
        products.add(new Product(3L, "XMAS T-Shirt 003", 30, "image3.jpg"));
        products.add(new Product(4L, "Gingerbread Card", 30, "image4.jpg"));
        products.add(new Product(5L, "Wrapping Paper 2022", 30, "image5.jpg"));

        return products;
    }
}
