package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;



@Controller
public class MarketplaceController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("home/marketplace")
    public ModelAndView getMarketplace() {
        ModelAndView modelAndView = new ModelAndView("marketplace/marketplace");
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return modelAndView;
    }

}



