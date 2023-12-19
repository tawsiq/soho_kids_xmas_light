package uk.ac.cf.client1.team7sohokidschristmaslights.checkout;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.CartItemDTO;
import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.MarketService;
import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.Product;
import java.util.List;
import java.util.Optional;


@Controller
public class CartServiceController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("home/checkout")
    public ModelAndView getCheckout() {
        ModelAndView modelAndView = new ModelAndView("checkout/checkout"); // Refers to 'checkout.html'

        return modelAndView;
    }

    @Autowired
    private MarketService marketService; // Assuming this service provides access to product data

    @PostMapping("checkout/cart")
    public ModelAndView handleCartData(@RequestBody List<CartItemDTO> cartItems) {
        double totalCost = 0.0;
        boolean isValidCart = true;

        for (CartItemDTO item : cartItems) {
            Product product = marketService.getProduct(item.getProductId().intValue());
            if (product != null && product.getPrice().equals(item.getPrice())) {
                totalCost += item.getPrice() * item.getQuantity();
            } else {
                isValidCart = false;
                break;
            }
        }

        ModelAndView modelAndView = new ModelAndView("checkout/checkout");
        if (isValidCart) {
            modelAndView.addObject("cartItems", cartItems);
            modelAndView.addObject("totalCost", totalCost);
        } else {
            // Handle invalid cart scenario, e.g., redirect to an error page or show an error message
        }

        return modelAndView;
    }
}




