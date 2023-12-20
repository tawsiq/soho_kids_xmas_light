package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import java.io.IOException;
import java.util.List;

// Interface defining the business logic for marketplace operations
public interface MarketService {

    // Retrieve a single product by its ID
    Product getProduct(Integer id);

    // Retrieve image data for a given product
    // Throws IOException in case of an error reading the image file
    byte[] getProductImageData(Product productImage) throws IOException;

    // Retrieve a list of all products
    List<Product> getProductList();

    // Save checkout information to the database
    void saveCheckout(Checkout checkout);
}
