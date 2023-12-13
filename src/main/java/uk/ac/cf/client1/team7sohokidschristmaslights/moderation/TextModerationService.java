package uk.ac.cf.client1.team7sohokidschristmaslights.moderation;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TextModerationService {

    public String moderateText(String textToModerate) {
        try {
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

    private static final List<String> profaneWords = Arrays.asList(
            "areallybadword",
            "profane_word_2",
            // Add other profane words here
            "profane_word_n"
    );

    private static boolean containsProfanity(String text) {
        String lowercaseText = text.toLowerCase(); // Convert text to lowercase for case-insensitive matching

        // Remove special characters from the text
        String textWithoutSpecialChars = lowercaseText.replaceAll("[^a-zA-Z0-9]+", "");

        // Check if the text without special characters contains any profane words
        for (String word : profaneWords) {
            if (textWithoutSpecialChars.contains(word)) {
                return true; // Profanity found
            }
        }
        return false; // No profanity found
    }

    private static String censorProfanity(String text) {
        StringBuilder censoredText = new StringBuilder(text);

        // Convert text to lowercase for case-insensitive matching
        String lowercaseText = text.toLowerCase();

        // Replace profane words with asterisks
        for (String word : profaneWords) {
            // Generate a pattern that matches the word with any special characters
            Pattern pattern = generateWordPattern(word);
            Matcher matcher = pattern.matcher(lowercaseText);

            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                // Replace the word in the original text with asterisks
                for (int i = start; i < end; i++) {
                    if (Character.isLetterOrDigit(text.charAt(i))) {
                        censoredText.setCharAt(i, '*');
                    }
                }
            }
        }
        return censoredText.toString();
    }

    private static Pattern generateWordPattern(String word) {
        // Create a pattern for the word, ignoring special characters and spaces
        String patternString = Arrays.stream(word.split(""))
                .map(c -> Pattern.quote(c) + "[^a-zA-Z0-9]*") // Allow any special characters or spaces between letters
                .reduce((s1, s2) -> s1 + s2)
                .orElse("");

        return Pattern.compile("(?i)" + patternString, Pattern.CASE_INSENSITIVE);
    }
}
