package safeforhall.ui;

import java.util.function.Predicate;

public class SuggestionPredicate implements Predicate<String> {
    private final String suggestionPart;

    /**
     * Creates a SuggestionPredicate
     */
    public SuggestionPredicate(String suggestionPart) {
        this.suggestionPart = suggestionPart;
    }

    /**
     * Tests if the given {@code parameterPart} matches any of the given pattern.
     *
     * @return true if the pattern is matched
     */
    @Override
    public boolean test(String parameterPart) {
        boolean isOneCharPrefix = parameterPart.length() > 1 && parameterPart.charAt(1) == '/'
                && suggestionPart.substring(0, 2).equals(parameterPart.substring(0, 2));
        boolean isTwoCharPrefix = parameterPart.length() > 2 && parameterPart.charAt(2) == '/'
                && suggestionPart.substring(0, 3).equals(parameterPart.substring(0, 3));
        boolean isIndex = (suggestionPart.equals("INDEXES") || suggestionPart.equals("INDEX")
                || suggestionPart.equals("[INDEXES]") || suggestionPart.equals("[INDEX]"))
                && parameterPart.matches("\\d+");
        boolean isImportOrExport = (suggestionPart.equals("CSV_NAME") || suggestionPart.equals("FILE_NAME"))
                && parameterPart.matches("\\w+");
        boolean isLateKeyword = parameterPart.contains("k/l") && suggestionPart.equals("d2/DATE");
        boolean isOptionalOneCharPrefix = suggestionPart.charAt(0) == '['
                && parameterPart.length() > 1
                && parameterPart.charAt(1) == '/'
                && suggestionPart.substring(1, 3).equals(parameterPart.substring(0, 2));
        boolean isOptionalTwoCharPrefix = suggestionPart.charAt(0) == '['
                && parameterPart.length() > 2
                && parameterPart.charAt(2) == '/'
                && suggestionPart.substring(1, 4).equals(parameterPart.substring(0, 3));

        return isOneCharPrefix || isTwoCharPrefix || isIndex || isImportOrExport || isOptionalOneCharPrefix
                || isOptionalTwoCharPrefix || isLateKeyword;
    }

    /**
     * Checks if the given {@code parameterPart} is a late keyword.
     *
     * @return true if the parameterPart contains "k/l"
     */
    public boolean isLateKeyword(String parameterPart) {
        return parameterPart.contains("k/l") && suggestionPart.equals("d2/DATE");
    }
}
