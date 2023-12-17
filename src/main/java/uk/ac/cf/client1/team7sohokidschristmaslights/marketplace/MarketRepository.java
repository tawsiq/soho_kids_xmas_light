package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import java.util.List;



// Interface for the marketplace repository, defining essential data access operations
public interface MarketRepository {

    List<Product> getProductList(); // Retrieves a list of all products from the database
    Product getProduct(Integer id); // Retrieves a single product from the database based on its ID
    void saveCheckout(Checkout checkout); // Saves checkout information into the database

}
