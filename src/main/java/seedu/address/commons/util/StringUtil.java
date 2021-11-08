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
     * Returns true if the {@code sentence} contains the {@code text}.
     *   Ignores case and matches partial words
     *   <br>examples:<pre>
     *       containsTextIgnoreCase("ABc def", "abc") == true
     *       containsTextIgnoreCase("ABc def", "DEF") == true
     *       containsTextIgnoreCase("ABc def", "AB") == true // partial words match, unlike containsWordIgnoreCase
     *       </pre>
     * @param sentence cannot be null
     * @param text cannot be null, cannot be empty, must be a single word
     */

    public static boolean containsTextIgnoreCase(String sentence, String text) {
        requireNonNull(sentence);
        requireNonNull(text);

        String preppedWord = text.trim();
        checkArgument(!preppedWord.isEmpty(), "Text parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Text parameter should be a single word");

        return sentence.toLowerCase().contains(preppedWord.toLowerCase());
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
     * Removes flags (words starting with "-" character) from a string
     * @param s input string
     * @return string with flags removed
     */
    public static String stripFlags(String s) {
        requireNonNull(s);
        String[] result = Arrays.stream(s.split("\\s+"))
                .filter(word -> !word.startsWith("-"))
                .toArray(String[]::new);
        return String.join(" ", result);
    }

    /**
     * Removes extra whitespace characters found within the command
     * @param s input string
     * @return string with extra whitespace characters replaced with a single space
     */
    public static String removeExtraWhitespace(String s) {
        requireNonNull(s);
        return String.join(" ", s.split("\\s+")).trim();
    }

    /**
     * Checks if a sentence starts with a given command
     *
     * The command must be followed by a whitespace character or the end of the string
     *
     * @param sentence Sentence to check if it starts with a given command, which may have leading/trailing spaces
     * @param command Given command, which may have spaces in the middle but not leading/trailing
     * @return Whether the given sentence starts with the given command
     */
    public static boolean startsWithCommand(String sentence, String command) {
        assert command.trim().equals(command); // command should not have leading or trailing spaces
        requireNonNull(command);
        String cleanSentence = removeExtraWhitespace(sentence);
        return cleanSentence.startsWith(command + " ") || cleanSentence.equals(command);
    }
}
