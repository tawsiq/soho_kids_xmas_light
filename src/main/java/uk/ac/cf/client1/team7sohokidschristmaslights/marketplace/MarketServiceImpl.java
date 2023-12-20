package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service // Marks this class as a Spring Service component
public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository; // Repository for accessing data layer

    // Constructor for dependency injection of MarketRepository
    public MarketServiceImpl(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    // Retrieves a product by its ID using the MarketRepository
    @Override
    public Product getProduct(Integer id) {
        return marketRepository.getProduct(id);
    }

    // Retrieves the image data for a given product
    @Override
    public byte[] getProductImageData(Product productImage) throws IOException {
        Path pathToImage = Paths.get(productImage.getFilepath());
        return Files.readAllBytes(pathToImage); // Reads image file into a byte array
    }

    // Retrieves a list of all products from the MarketRepository
    @Override
    public List<Product> getProductList() {
        return marketRepository.getProductList();
    }

    // Saves checkout information using the MarketRepository
    @Override
    public void saveCheckout(Checkout checkout) {
        marketRepository.saveCheckout(checkout);
    }
}
