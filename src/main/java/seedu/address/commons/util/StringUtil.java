package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Returns true if the {@code sentence} contains the {@code wordsList} in the expected order.
     *   Ignores case, but words in the wordsList need to partially match words in the sentence and their relative
     *   ordering.
     *
     *   <br>examples:<pre>
     *       containsWordsInOrderIgnoreCase("ABc def", "abc") == true
     *       containsWordsInOrderIgnoreCase("ABc def", "ab d") == true
     *       containsWordsInOrderIgnoreCase("ABc def", "def abc") == false
     *       </pre>
     * @param sentence cannot be null
     * @param wordsList cannot be null, cannot be empty
     */
    public static boolean containsWordsInOrderIgnoreCase(String sentence, List<String> wordsList) {
        requireNonNull(sentence);
        requireNonNull(wordsList);
        checkArgument(!wordsList.isEmpty(), "Words parameter cannot be empty");

        List<String> wordsInPreppedSentence = new ArrayList<>(List.of(sentence.split("\\s+")));
        int wordsIndex = 0;
        for (int i = 0; i < wordsList.size(); i++) {
            wordsList.set(i, wordsList.get(i).trim().toLowerCase());
        }
        for (int i = 0; i < wordsInPreppedSentence.size(); i++) {
            wordsInPreppedSentence.set(i, wordsInPreppedSentence.get(i).trim().toLowerCase());
        }

        for (String s : wordsInPreppedSentence) {
            if (s.startsWith(wordsList.get(wordsIndex))) {
                if (++wordsIndex == wordsList.size()) {
                    return true;
                }
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
        return t.getMessage() + "\n" + sw;
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
     * Concatenate "s" at the back of the string if size is bigger than 1.
     * @param string singular form
     * @param size of list
     * @return singular or plural form of the string given
     */
    public static String singularOrPlural(String string, int size) {
        return size > 1 ? string + "s" : string;
    }
}
