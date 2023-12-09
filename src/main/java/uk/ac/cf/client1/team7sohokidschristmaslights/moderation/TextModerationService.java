package uk.ac.cf.client1.team7sohokidschristmaslights.moderation;

import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.Entity;
import com.textrazor.annotations.AnalyzedText;

import com.textrazor.annotations.ScoredCategory;
import org.springframework.stereotype.Service;

import java.util.List;
// CREDIT FOR THIS GOES TO CHATGPT. I only helped debug it and correct it based on the documentation. I understand what's going on!
@Service
public class TextModerationService {

    private static final String API_KEY = "104b06399ec4350e4ecf30fd6b4a3b75c4c3713e57017c1e0f3cf3dc";

    private final TextRazor client;

    public TextModerationService() {
        client = new TextRazor(API_KEY); // Initialize TextRazor client with your API key
        client.addExtractor("entities"); // Add the entities extractor for analysis
        client.addExtractor("categories"); // Add the categories extractor for analysis
    }
    //------ PUBLIC METHODS ------//
    public String moderateText(String textToModerate) {
        try{
            if (isTextSafe(textToModerate)) {
                return textToModerate; // Text is safe, return unchanged
            } else {
                // Text is not safe, censor or modify the text here
                return censorText(textToModerate); // Modify this method for your censorship logic
            }
        } catch (Exception e) {
            System.out.printf("System failed to moderate " + textToModerate + "%n");
            e.printStackTrace();
            return "null moderation operation %n";
        }

    }

    //------ PRIVATE METHODS ------//
    private boolean isTextSafe(String textToAnalyze) {
        // Analyze the text for entities and categories
        AnalyzedText response;
        try {
            response = client.analyze(textToAnalyze);
        } catch (NetworkException | AnalysisException e) {
            throw new RuntimeException(e);
        }

        // Check entities for profanity or offensive terms
        if (response.getResponse().getEntities() != null) {
            for (Entity entity : response.getResponse().getEntities()) {
                // Check if the entity is recognized as profane or offensive
                if (isProfane(entity) || isOffensive(entity)) {
                    return false; // Contains profanity or offensive content
                }
            }
            // Check categories for potentially offensive content
            for (ScoredCategory category : response.getResponse().getCategories()) {
                // Check if the category score is above the threshold and recognized as offensive
                if (category.getScore() >= 0.5 && isCategoryOffensive(category.getLabel())) {
                    return false; // Contains offensive content above the threshold score
                }
            }
        }
        return true; // Text is safe
    }

    private boolean isCategoryOffensive(String categoryLabel) {
        // Check if the category label is recognized as offensive
        // Modify the logic based on TextRazor's category taxonomy
        return categoryLabel.toLowerCase().contains("offensive");
    }

    private String censorText(String textToCensor) {
        // Example: Replace offensive words with asterisks
        return textToCensor.replaceAll("\\b(offensive_word1|offensive_word2)\\b", "***");
    }

    private boolean isProfane(Entity entity) {
        // Check if any type within the list is recognized as "Profanity"
        List<String> types = entity.getType();
        for (String type : types) {
            if (type.equals("Profanity")) {
                return true; // Contains profanity
            }
        }
        return false; // Does not contain profanity
    }

    private boolean isOffensive(Entity entity) {
        // Check if any type within the list is recognized as "Offensive"
        List<String> types = entity.getType();
        for (String type : types) {
            if (type.equals("Offensive")) {
                return true; // Contains offensive content
            }
        }
        return false; // Does not contain offensive content
    }

}
