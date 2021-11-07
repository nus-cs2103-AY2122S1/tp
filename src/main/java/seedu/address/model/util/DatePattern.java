package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Encapsulates the accepted date patterns.
 */
public enum DatePattern {

    PATTERN1("dd MMM uuuu"),
    PATTERN2("uuuu-MM-dd"),
    PATTERN3("dd-MM-uuuu"),
    PATTERN4("uuuu/MM/dd"),
    PATTERN5("dd/MM/uuuu"),
    PATTERN6("dd MMMM uuuu");

    private final String pattern;

    DatePattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }

    /**
    * Returns a concatenated list of all the patterns in the DatePattern.
    *
    * @return concatenated String of patterns.
    */
    public static String printPatterns() {
        List<String> patterns = Arrays.stream(DatePattern.values())
                .map(DatePattern::getPattern)
                .collect(Collectors.toList());
        String rawPatterns = String.join("\n", patterns);
        String toDisplay = rawPatterns.replaceAll("u", "y");
        return toDisplay;
    }
}
