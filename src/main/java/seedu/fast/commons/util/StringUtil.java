package seedu.fast.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code query}.
     *   Ignores case, full word match is not required but the word in the
     *   {@code sentence} must start with the {@code query}.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "Ab") == true
     *       containsWordIgnoreCase("ABc def", "Bc") == false
     *       </pre>
     * @param sentence cannot be null
     * @param query cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsQueryIgnoreCase(String sentence, String query) {
        requireNonNull(sentence);
        requireNonNull(query);

        String preppedWord = query.trim();
        checkArgument(!preppedWord.isEmpty(), "Query parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Query parameter should be a single word");
        String preppedWordLowerCase = preppedWord.toLowerCase();
        // ignore case

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        for (String sentenceWord:wordsInPreppedSentence) {
            String sentenceWordLowerCase = sentenceWord.toLowerCase();
            //ignore case
            if (sentenceWordLowerCase.startsWith(preppedWordLowerCase)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
