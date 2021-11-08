package seedu.programmer.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code phrase}.
     *   Ignores case, but a full character sequence match is required.
     *   <br>examples:<pre>
     *       containsPhraseIgnoreCase("ABc def", "abc") == true
     *       containsPhraseIgnoreCase("ABc def", "DEF") == true
     *       containsPhraseIgnoreCase("ABc def", "AB") == true
     *       containsPhraseIgnoreCase("ABc def", "ABcdef") == false // not a full char sequence match
     *       </pre>
     *
     * @param sentence cannot be null.
     * @param phrase cannot be null, cannot be empty, need not be a single word.
     * @return true if the {@code phrase} is contained in the {@code sentence}.
     */
    public static boolean containsPhraseIgnoreCase(String sentence, String phrase) {
        requireNonNull(sentence);
        requireNonNull(phrase);

        String preppedPhrase = phrase.trim();
        checkArgument(!preppedPhrase.isEmpty(), "Word parameter cannot be empty");

        String preppedSentence = sentence.toLowerCase();

        return preppedSentence.contains(preppedPhrase.toLowerCase());
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw;
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer.
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE}. <br>
     * Will return false for any other non-null string input.
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters).
     *
     * @param s The String to be checked if it is a non-zero unsigned integer.
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
