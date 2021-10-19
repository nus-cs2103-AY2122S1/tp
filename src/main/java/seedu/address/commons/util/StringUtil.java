package seedu.address.commons.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code stringOfWords} in the expected order.
     *   Ignores case, but words in the stringOfWords need to partially match words in the sentence and their relative
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

    public static boolean isSubsequence(String subsequence, String word) {
        int index = -1;
        for (int i = 0; i < subsequence.length(); i++) {
            index = word.indexOf(subsequence.charAt(i), index + 1);
            if (index == -1)  {
                return false;
            }
        }
        return true;
    }

    public static boolean haveSameFirstCharacter(String word1, String word2) {
        requireNonNull(word1);
        requireNonNull(word2);
        return word1.charAt(0) == word2.charAt(0);
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
     * Concatenate "s" at the back of the string if size is bigger than 1.
     * @param string singular form
     * @param size of list
     * @return singular or plural form of the string given
     */
    public static String singularOrPlural(String string, int size) {
        return size > 1 ? string + "s" : string;
    }
}
