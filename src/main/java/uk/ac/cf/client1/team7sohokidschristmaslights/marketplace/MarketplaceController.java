package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MarketplaceController {

    @GetMapping("home/marketplace")
    public ModelAndView getMarketplace() {
        ModelAndView modelAndView = new ModelAndView("marketplace/marketplace");

        return modelAndView;
    }
}
