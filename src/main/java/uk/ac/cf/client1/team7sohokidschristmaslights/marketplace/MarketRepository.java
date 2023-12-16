package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import java.util.List;

public interface MarketRepository {
    List<Product> getProductList();
    Product getProduct(Integer id);
}
