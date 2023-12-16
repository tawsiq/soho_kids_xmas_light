package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MarketServiceImpl implements MarketService{

    private final MarketRepository marketRepository;

    public MarketServiceImpl(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }
    public Product getProduct(Integer id) {
        return marketRepository.getProduct(id);
    }
    public byte[] getProductImageData(Product productImage) throws IOException {

        Path pathToImage = Paths.get(productImage.getFilepath());
        // Read the image data into a byte array
        return Files.readAllBytes(pathToImage);
    }
    public List<Product> getProductList(){
        return marketRepository.getProductList();
    }
}
