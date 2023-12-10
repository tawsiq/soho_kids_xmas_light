package uk.ac.cf.client1.team7sohokidschristmaslights.moderation;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
// Pair Programmed with ChatGPT
@Service
public class TextModerationService {

    public String moderateText(String textToModerate) {
        try{
            if (containsProfanity(textToModerate)) {
                return censorProfanity(textToModerate);

            } else {
                return textToModerate;
            }
        } catch (Exception e) {
            System.out.printf("System failed to moderate " + textToModerate + "%n");
            e.printStackTrace();
            return "null moderation operation %n";
        }

    }

    // List of profane words
    private static final List<String> profaneWords = Arrays.asList(
            "areallybadword",
            "profane_word_2",
            // Add other profane words here
            "profane_word_n"
    );

    // Check if text contains profanity
    private static boolean containsProfanity(String text) {
        // Convert text to lowercase for case-insensitive matching
        String lowercaseText = text.toLowerCase();

        // Check if the text contains any profane words
        for (String word : profaneWords) {
            if (lowercaseText.contains(word)) {
                return true; // Profanity found
            }
        }
        return false; // No profanity found
    }

    // Replace profanity in text with asterisks
    private static String censorProfanity(String text) {
        // Use a StringBuilder to maintain the original case
        StringBuilder censoredText = new StringBuilder(text);

        for (String word : profaneWords) {
            int index = 0;
            // Replace profane words while maintaining the original case
            while ((index = censoredText.toString()
                            .toLowerCase()
                            .indexOf(word.toLowerCase(), index)) != -1) {

                censoredText.replace(index, index + word.length(), "*".repeat(word.length())); // Replace word with asterisks
                index += word.length(); // Move the index forward after replacement
            }
        }
        return censoredText.toString();
    }
}

