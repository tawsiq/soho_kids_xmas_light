package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import java.io.IOException;

import java.util.List;

public interface MarketService {
    Product getProduct(Integer id);
    byte[] getProductImageData(Product productImage) throws IOException;
    List<Product> getProductList();
}
