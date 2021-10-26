package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class WordSuggestionTest {
    private final List<String> validWords = Arrays.asList("Singapore", "Indonesia", "Malaysia", "Philippines");

    @Test
    public void computeSingleLevenshteinDistance_validInput_success() {

        assertEquals(1, WordSuggestion.computeSingleLevenshteinDistance("abc", "ab"));

        // case-insensitive
        assertEquals(1, WordSuggestion.computeSingleLevenshteinDistance("AbC", "ab"));

        assertEquals(16, WordSuggestion.computeSingleLevenshteinDistance("bookasdasasda",
                "backaskxvcdrqnwbwfsahas"));
    }

    @Test
    public void getSuggestedWordsList_validInput_success() {
        assertEquals(List.of("Singapore"), new WordSuggestion("Singopear", validWords).getSuggestedWordsList());

        assertEquals(List.of("Indonesia"), new WordSuggestion("Indo", validWords).getSuggestedWordsList());

        assertEquals(List.of("Malaysia"), new WordSuggestion("malaysao", validWords).getSuggestedWordsList());
    }

    @Test
    public void getSuggestedWords_validInput_success() {
        assertEquals(String.format(WordSuggestion.MESSAGE, "Singapore"),
                new WordSuggestion("Singopear", validWords).getSuggestedWords());

        assertEquals(String.format(WordSuggestion.MESSAGE, "Indonesia"),
                new WordSuggestion("Indo", validWords).getSuggestedWords());
    }
}
