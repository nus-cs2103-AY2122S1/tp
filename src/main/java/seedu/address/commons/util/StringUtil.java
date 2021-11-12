package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    private static final String MESSAGE_WORD_CANNOT_EMPTY = "Word parameter cannot be empty";
    private static final String MESSAGE_WORD_SINGLE = "Word parameter should be a single word";
    private static final String MESSAGE_SUBSTRING_CANNOT_EMPTY = "Substring parameter cannot be empty";

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     * Ignores case, but a full word match is required.
     * <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     *
     * @param sentence Sentence string to be checked and cannot be null
     * @param word     Word to search and cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), MESSAGE_WORD_CANNOT_EMPTY);
        checkArgument(preppedWord.split("\\s+").length == 1, MESSAGE_WORD_SINGLE);

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     * Ignores case and full word match is not required.
     * <br>examples:<pre>
     *       containsSubstringIgnoreCase("ABc def", "abc") == true
     *       containsSubstringIgnoreCase("ABc def", "DEF") == true
     *       containsSubstringIgnoreCase("ABc def", "AB") == true
     *       </pre>
     *
     * @param sentence  Sentence string to be checked and cannot be null.
     * @param substring String to search and cannot be null, cannot be empty.
     */
    public static boolean containsSubstringIgnoreCase(String sentence, String substring) {
        requireNonNull(sentence);
        requireNonNull(substring);

        String preppedSubstring = substring.trim().toLowerCase();
        checkArgument(!preppedSubstring.isEmpty(), MESSAGE_SUBSTRING_CANNOT_EMPTY);

        String preppedSentence = sentence.toLowerCase();

        return preppedSentence.contains(preppedSubstring);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     *
     * @param t The throwable to get message from.
     * @return The detailed message and stack trace of t.
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

    /**
     * Strips leading 0s from a string.
     *
     * @param s String to modify.
     * @return Stripped string.
     */
    public static String stripLeadingZeroes(String s) {
        String regex = "^0+(?!$)";
        return s.replaceAll(regex, "");
    }
}
