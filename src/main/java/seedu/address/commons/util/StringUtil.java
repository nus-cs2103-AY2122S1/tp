package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {
    private static final long DEFAULT_RANDOM_SEED = 10L;

    /**
     * Returns true if any of the phrases in {@code sentence} starts with the {@code query}.
     * Ignores case.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DE") == true
     *       containsWordIgnoreCase("ABc def", "def ghi") == false
     *       </pre>
     * @param sentence cannot be null
     * @param query cannot be null
     */
    public static boolean phrasesStartsWithQuery(String sentence, String query) {
        requireNonNull(sentence);
        requireNonNull(query);

        String preppedQuery = query.trim().toLowerCase();
        checkArgument(!preppedQuery.isEmpty(), "query parameter cannot be empty");

        String preppedSentence = sentence.toLowerCase();
        // Indexes of start of each word in the sentence
        IntStream phrasesIndexes = IntStream.range(0, sentence.length() - 1).filter(
                x -> sentence.charAt(x) != ' '
                        && (x == 0 || sentence.charAt(x - 1) == ' ')
        );

        // True if any phrase starts with the query
        return phrasesIndexes
                .anyMatch(i -> preppedSentence.startsWith(preppedQuery, i));
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc def") == true
     *       containsWordIgnoreCase("ABc def", "abc DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsMultipleWord(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);
        if (sentence.length() == 0 || word.length() == 0) {
            return false;
        }

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        String[] wordsInPreppedWord = preppedWord.split("\\s+");
        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return StringUtil.equalArray(wordsInPreppedSentence, wordsInPreppedWord);
    }
    /**
     * Checks whether first array string contains the second array string in exact order
     * example: [aaa, bbb, ddd] contains [aaa, Bbb] but does not contain [aaa, ddd]
     */
    public static boolean equalArray (String [] first, String [] second) {
        int firstLength = first.length;
        int secondLength = second.length;
        if (firstLength == 0 || secondLength == 0) {
            return false;
        }
        boolean doesMatch = false;
        for (int i = 0; i < firstLength - secondLength + 1; i = i + 1) {
            doesMatch = doesMatch || StringUtil.equalArrayElements(first, second, i);
        }
        return doesMatch;
    }
    /**
     * Helper Function for equalArray
     * Checks whether first string array from firstStringIndex is the same as
     * second array string with all elements in same order
     */
    public static boolean equalArrayElements (String [] first, String [] second, int firstStringIndex) {
        if (firstStringIndex >= first.length) {
            return false;
        }
        int indexOfFirst = firstStringIndex;
        boolean doesMatch = true;
        for (int j = 0; j < second.length; j = j + 1) {
            if (!first[indexOfFirst].equalsIgnoreCase(second[j])) {
                doesMatch = doesMatch && false;
                break;
            }
            indexOfFirst = indexOfFirst + 1;
        }
        return doesMatch;
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
     * Generates a random alphanumeric string with default length 10
     * and the given random seed.
     * Credit to: https://www.baeldung.com/java-random-string
     */
    public static String generateRandomString(long randomSeed) {
        final int leftLimit = 97; // letter 'a'
        final int rightLimit = 122; // letter 'z'
        int defaultLength = 10;
        Random random = new Random(randomSeed);

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(defaultLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /**
     * Generates a random alphanumeric string with default random seed as system time.
     */
    public static String generateRandomString() {
        return generateRandomString(new Random().nextInt());
    }
}
