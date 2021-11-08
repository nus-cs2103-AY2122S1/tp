package seedu.tuitione.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.util.AppUtil.checkArgument;

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
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
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
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case and checks if sentence matches word prefix.
     *
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean startsWithWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim().toLowerCase();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence.toLowerCase();
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(w -> w.startsWith(preppedWord));
    }

    /**
     * Capitalizes the word or sentence {@code string}. Trims the word as well.
     */
    public static String capitalizeFirstCharAndLowerRest(String string) {
        requireNonNull(string);
        String trimmedString = string.trim();
        if (trimmedString.length() == 0) {
            return trimmedString;
        }
        return trimmedString.substring(0, 1).toUpperCase() + trimmedString.substring(1).toLowerCase();
    }

    /**
     * Returns a prepared String, where only the first character of each word is a capital letter
     * while the rest are lower cased. Utilises {@code capitalizeFirstCharAndLowerRest} method.
     */
    public static String capitaliseFirstCharOfEachWord(String toPrepare) {
        String[] wordArr = toPrepare.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < wordArr.length; idx++) {
            String word = wordArr[idx];
            sb.append(StringUtil.capitalizeFirstCharAndLowerRest(word.trim()));
            if (idx < wordArr.length - 1) {
                sb.append(' ');
            }
        }
        return sb.toString();
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

    /**
     * Returns true if {@code s} represents a numbered string, with negatives allowed.
     * Possible values are '1234' and '-12344'.
     * Will return false for any other non-null string input
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isAStringedNumber(String s) {
        requireNonNull(s);
        String trimmedS = s.trim();
        if (trimmedS.startsWith("-")) {
            trimmedS = trimmedS.substring(1);
        }
        return !trimmedS.isEmpty() && trimmedS.matches("\\d+");
    }
}
