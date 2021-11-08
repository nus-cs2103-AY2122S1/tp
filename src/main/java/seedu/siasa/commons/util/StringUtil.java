package seedu.siasa.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.util.AppUtil.checkArgument;
import static seedu.siasa.commons.util.CollectionUtil.requireAllNonNull;

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
     * Returns true if {@code a} and {@code b} have an edit distance of one, i.e. One operation is needed
     * to transform {@code a} to {@code b}. Otherwise, return false.
     * @throws NullPointerException if {@code a} or {@code b} is null.
     */
    // Method adapted from: https://www.geeksforgeeks.org/check-if-two-given-strings-are-at-edit-distance-one/
    public static boolean isEditDistanceOne(String a, String b) {
        requireAllNonNull(a, b);

        int lenA = a.length();
        int lenB = b.length();
        int lenDiff = Math.abs(lenA - lenB);

        int countEdits = 0;
        int idxA = 0;
        int idxB = 0;

        if (lenDiff > 1) {
            return false;
        }
        while (idxA < lenA && idxB < lenB) {
            if (a.charAt(idxA) != b.charAt(idxB)) {
                if (countEdits == 1) {
                    return false;
                } else if (lenA > lenB) {
                    idxA++;
                } else if (lenA < lenB) {
                    idxB++;
                } else {
                    idxA++;
                    idxB++;
                }
                countEdits++;
            } else {
                idxA++;
                idxB++;
            }
        }

        if (idxA < lenA || idxB < lenB) {
            countEdits++;
        }

        return countEdits == 1;
    }

    /**
     * Returns true if two strings are similar, defined as the strings
     * having an edit distance of zero or one (case insensitive).
     */
    public static boolean isSimilar(String a, String b) {
        if (a.equals(b)) {
            return false;
        }
        String uppercaseA = a.toUpperCase();
        String uppercaseB = b.toUpperCase();
        return uppercaseA.equals(uppercaseB)
                || isEditDistanceOne(uppercaseA, uppercaseB);

    }
}
