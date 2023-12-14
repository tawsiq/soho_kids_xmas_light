//package uk.ac.cf.client1.team7sohokidschristmaslights.homepage;
//
//import uk.ac.cf.client1.team7sohokidschristmaslights.submissions.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import uk.ac.cf.client1.team7sohokidschristmaslights.submissions.ImageService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/images")
//public class ImageController {
//
//    private final ImageService imageService;
//    private final ImageRepository imageRepository;
//
//    @Autowired
//    public ImageController(ImageRepository animageRepository, ImageService anImageService) {
//        this.imageRepository = animageRepository;
//        this.imageService = anImageService;
//    }
//
//    @GetMapping
//    public List<String> getImageUrls() {
//        List<Image> images = imageService.getImageItemList();
//        return images.stream().map(Image::getUrl).toList();
//    }
//}
