package seedu.anilist.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case and does not require full match.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == true
     *       </pre>
     * @param sentence cannot be null
     * @param phrase cannot be null, cannot be empty
     */
    public static boolean containsPhraseIgnoreCase(String sentence, String phrase) {
        requireNonNull(sentence);
        requireNonNull(phrase);

        String preppedPhrase = phrase.trim().toLowerCase();
        checkArgument(!preppedPhrase.isEmpty(), "Phrase parameter cannot be empty");

        String preppedSentence = sentence.toLowerCase();

        return preppedSentence.contains(preppedPhrase);
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
     * Returns true if {@code s} represents a valid number
     * e.g. ..., -2, -1, 0, 1, 2, 3, ... <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isValidNumber(String s) {
        return s.matches("[\\-]?[0-9]+");
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        return isIntegerInRange(s, 1, Integer.MAX_VALUE);
    }

    /**
     * Returns true if {@code s} represents an integer within {@code start} and {@code end}
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isIntegerInRange(String s, int start, int end) {
        requireNonNull(s);
        if (!isValidNumber(s)) {
            return false;
        }

        try {
            int value = Integer.parseInt(s);
            return start <= value && value <= end
                && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
