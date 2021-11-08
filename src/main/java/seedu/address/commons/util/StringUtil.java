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

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     *
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     * @return true if the {@code sentence} contains the {@code word}, false otherwise
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     *
     * @param t {@code Throwable} for which we are returning a detailed message of, cannot be null
     * @return detailed message of the given {@code Throwable} with its stack trace
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
     *
     * @param s {@code String} to be checked whether it represents a non-zero unsigned integer, cannot be null
     * @return true if {@code s} represents a non-zero unsigned integer, false otherwise
     * @throws NullPointerException if {@code s} is null
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
     * Returns true if {@code s} represents an unsigned integer
     * e.g. 0, 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     *
     * @param s {@code String} to be checked whether it represents an unsigned integer, cannot be null
     * @return true if {@code s} represents an unsigned integer, false otherwise
     * @throws NullPointerException if {@code s} is null
     */
    public static boolean isUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value >= 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Removes any leading 0s in {@code s}.
     *
     * @param s {@code String} to remove leading zeroes, cannot be null
     * @return {@code s} but with its leading zeroes removed
     */
    public static String removeLeadingZeroes(String s) {
        requireNonNull(s);

        int index = 0;
        while (index < s.length() && s.charAt(index) == '0') {
            index++;
        }
        return s.substring(index);
    }

    /**
     * Returns true if {@code s1} and {@code s2} represent unsigned integers with a difference of at most {@code n}.
     * Unsigned integers: 0, 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false if {@code s1} or {@code s2} are other non-null string inputs that are not unsigned integers,
     * or if the difference of their integer representations are more than n.
     * Other non-null string inputs: empty string, "-1", "0", "+1", and " 2 " (untrimmed),
     * "3 0" (contains whitespace), "1 a" (contains letters)
     *
     * @param s1 {@code String} to be compared with {@code s2}, cannot be null
     * @param s2 {@code String} to be compared with {@code s1}, cannot be null
     * @param n given range
     * @return true if {@code s1} and {@code s2} represent unsigned integers
     * and are within {@code n} range from each other, false otherwise
     * @throws NullPointerException if {@code s1} or {@code s2} are null
     */
    public static boolean areUnsignedIntegersWithinRange(String s1, String s2, int n) {
        CollectionUtil.requireAllNonNull(s1, s2);

        try {
            int value1 = Integer.parseInt(s1);
            int value2 = Integer.parseInt(s2);
            return isUnsignedInteger(s1) && isUnsignedInteger(s2) && Math.abs(value1 - value2) <= n;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if the double represented by {@code s1} is larger than or equals to that by {@code s2}.
     *
     * @param s1 {@code String} to be compared with {@code s2}, cannot be null
     * @param s2 {@code String} to be compared with {@code s1}, cannot be null
     * @return true if {@code s1} and {@code s2} represent doubles
     * and {@code s1} is larger than or equals to {@code s2}, false otherwise
     * @throws NullPointerException if {@code s1} or {@code s2} are null.
     */
    public static boolean isDoubleLargerOrEqualToValue(String s1, String s2) {
        CollectionUtil.requireAllNonNull(s1, s2);
        try {
            double value1 = Double.parseDouble(s1);
            double value2 = Double.parseDouble(s2);
            return (value1 >= value2);
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
