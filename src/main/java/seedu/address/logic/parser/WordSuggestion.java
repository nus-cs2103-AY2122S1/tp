package seedu.address.logic.parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Contains utility methods used for providing suggestions for wrongly typed words.
 * Computed using the Levenshtein distance, which is the minimum number of single-character edits between two words.
 */
public class WordSuggestion {

    public static final String MESSAGE = "Did you mean %s?";

    private final String word;
    private final List<String> validWords;
    private final Map<String, Integer> editDistances = new HashMap<>();
    private int minDistance = Integer.MAX_VALUE;

    public WordSuggestion(String word, List<String> correctWords) {
        this.word = word;
        this.validWords = correctWords;

        computeAllLevenshteinDistance();
    }

    private static Integer[][] createOneBasedZeroMatrix(int row, int col) {
        Integer[][] matrix = new Integer[row+1][col+1];

        for (int i = 0; i < row + 1; i++) {
            Integer[] newRow = new Integer[col+1];
            matrix[i] = newRow;
        }

        return matrix;
    }

    public static int computeSingleLevenshteinDistance(String word1, String word2) {
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        Integer[][] matrix = createOneBasedZeroMatrix(word1.length(), word2.length());

        for (int i = 0; i < word1.length()+1; i++) {
            matrix[i][0] = i;
        }

        for (int i = 0; i < word2.length()+1; i++) {
            matrix[0][i] = i;
        }

        for (int i = 1; i < word1.length()+1; i++) {
            for (int j = 1; j < word2.length()+1; j++) {
                int substitutionCost = 0;
                if (word1.charAt(i-1) != word2.charAt(j-1)) {
                    substitutionCost = 1;
                }

                matrix[i][j] = Math.min(Math.min(
                        matrix[i-1][j] + 1, // deletion (delete last char of word2)
                        matrix[i][j-1] + 1), // insertion (insert last char of word1)
                        matrix[i-1][j-1] + substitutionCost); // substitution (substitute last char of word1 and word2)
            }
        }

        return matrix[word1.length()][word2.length()];
    }

    private void computeAllLevenshteinDistance() {
        for (String validWord : validWords) {
            int editDistance = computeSingleLevenshteinDistance(this.word, validWord);
            minDistance = Math.min(minDistance, editDistance);

            editDistances.put(validWord, editDistance);
        }
    }

    public List<String> getSuggestedWordsList() {
        List<String> suggestions = new LinkedList<>();

        for (Map.Entry<String, Integer> entry : editDistances.entrySet()) {
            String suggestedWord = entry.getKey();
            int distance = entry.getValue();

            if (distance == minDistance) {
                suggestions.add(suggestedWord);
            }
        }

        return suggestions;
    }

    public String getSuggestedWords() {
        return String.format(MESSAGE, String.join(",", getSuggestedWordsList()));
    }
}
