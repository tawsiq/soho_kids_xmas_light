package uk.ac.cf.client1.team7sohokidschristmaslights.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class CartServiceController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("home/checkout")
    public ModelAndView getCheckout() {
        ModelAndView modelAndView = new ModelAndView("checkout/checkout"); // Refers to 'checkout.html'

        return modelAndView;
    }



}
