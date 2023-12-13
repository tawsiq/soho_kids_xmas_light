import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MarketplaceController {

    private final ProductService productService;

    @Autowired
    public MarketplaceController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/marketplace")
    public String getMarketplace(Model model) {
        // Add logic to fetch products from your data source
        List<Product> products = productService.getAllProducts();

        // Add the products to the model
        model.addAttribute("products", products);

        // Return the Thymeleaf template name
        return "marketplace";
    }
}
