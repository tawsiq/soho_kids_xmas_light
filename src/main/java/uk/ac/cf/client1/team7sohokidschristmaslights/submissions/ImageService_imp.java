package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import org.springframework.stereotype.Service;

import java.util.List;

//TODO: Updated MenuService method implementations (jdbc) go here

@Service
public class ImageService_imp implements ImageService{

    private final ImageRepository imageRepository;
    // TODO: Test warning fix: Makes ^ final.
    public ImageService_imp(ImageRepository anImageRepository) {
        this.imageRepository = anImageRepository;
    }
    // Implement the ImageService Methods using the repository.
    public List<ImageClass> getImageItemList() {
        return imageRepository.getImageItemList();
    }
    public ImageClass getImage(Long id) {
        return imageRepository.getImage(id);
    }
}
