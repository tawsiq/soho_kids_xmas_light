package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.List;

@Controller
public class MarketplaceController {

    private final MarketService marketService; // Service for handling marketplace operations


    // Constructor injection of MarketService
  public MarketplaceController(MarketService marketService){
        this.marketService = marketService;
  //      this.cartDetailRepository = cartDetailRepository;
  }


    // Handles requests to the marketplace page, displaying products
    @GetMapping("home/marketplace")
    public ModelAndView getMarketplace() {
        ModelAndView modelAndView = new ModelAndView("marketplace/marketplace");

        List<Product> productList = marketService.getProductList();
        modelAndView.addObject("productList", productList);

        return modelAndView;
    }


    // Handles requests for product images based on product ID
    @GetMapping("/getProductImage/{id}")
    public ResponseEntity<byte[]> getImageDataForTemplate(@PathVariable Integer id) throws IOException {

        Product product = marketService.getProduct(id);
        // Get image data bytes from the service method
        byte[] productImageData = marketService.getProductImageData(product);

        if (product.getFilename().endsWith("jpg")){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(productImageData);

        } else {
            // The only image types stored in the DB / file system are jpg & png
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(productImageData);
        }
    }

    // Handles GET requests for the checkout page
    @GetMapping("home/marketplace/checkout")
    public ModelAndView hostCheckout(){
        return new ModelAndView("checkout/checkout");
    }

    // Handles POST requests for processing the checkout with the total price and checkout details
    @PostMapping("home/marketplace/checkout/{total_price}")
    public ModelAndView processCheckout(@PathVariable Float total_price, Checkout checkout){

        checkout.setTotalPrice(total_price); // Set the total price of the checkout
        marketService.saveCheckout(checkout); // Save the checkout details


        return new ModelAndView("redirect:/home/marketplace/checkout"); // Redirect after successful checkout
    }
    // New method to process cart data
   // @PostMapping("/processCart")
    //public ResponseEntity<?> processCart(@RequestBody List<CartDetail> cartDetails) {
    //    for (CartDetail detail : cartDetails) {
     //       cartDetailRepository.save(detail);
     //   }
      //  return ResponseEntity.ok().build();
  //  }

}





