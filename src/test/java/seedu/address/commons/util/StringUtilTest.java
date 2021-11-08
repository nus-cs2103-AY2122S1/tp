package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroUnsignedInteger("")); // Boundary value
        assertFalse(StringUtil.isNonZeroUnsignedInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonZeroUnsignedInteger("a"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroUnsignedInteger("-1"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroUnsignedInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonZeroUnsignedInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroUnsignedInteger("1")); // Boundary value
        assertTrue(StringUtil.isNonZeroUnsignedInteger("10"));
    }


    //---------------- Tests for containsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "aaa BBB"));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *
     * Possible scenarios returning false:
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsWordIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsWordIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsWordIgnoreCase("    ", "123"));

        // Matches a full word only
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger than query word
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bbbb")); // Query word bigger than sentence word

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil.containsWordIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsWordIgnoreCase("AAA bBb ccc  bbb", "bbB"));
    }

    //---------------- Tests for containsTextIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsTextIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsTextIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsTextIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Text parameter cannot be empty", ()
            -> StringUtil.containsTextIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsTextIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Text parameter should be a single word", ()
            -> StringUtil.containsTextIgnoreCase("typical sentence", "aaa BBB"));
    }

    @Test
    public void containsTextIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsTextIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *   - matches a partial word in sentence
     *
     * Possible scenarios returning false:
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsTextIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsTextIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsTextIgnoreCase("    ", "123"));

        // Matches a partial word, different upper/lower case letters
        assertTrue(StringUtil.containsTextIgnoreCase("aaa bbb ccc", "bB")); // Sentence word bigger than query word
        assertFalse(StringUtil.containsTextIgnoreCase("aaa bbb ccc", "bbbb")); // Query word bigger than sentence word

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsTextIgnoreCase("aaa bBb ccc", "Aaa")); // First word (boundary case)
        assertTrue(StringUtil.containsTextIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsTextIgnoreCase("aaa bBb ccc@1", "Bbb")); // Middle word (boundary case)
        assertTrue(StringUtil.containsTextIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil.containsTextIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsTextIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsTextIgnoreCase("AAA bBb ccc  bbb", "bbB"));
    }

    //---------------- Tests for getDetails --------------------------------------

    /*
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void getDetails_exceptionGiven() {
        assertTrue(StringUtil.getDetails(new FileNotFoundException("file not found"))
            .contains("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getDetails(null));
    }

    //---------------- Tests for stripFlags --------------------------------------
    /*
     * Equivalence Partitions:
     * - null
     * - empty string
     * - string which is entirely normal words
     * - string with some flags and some normal words
     * - string which is entirely flags
     */

    @Test
    public void stripFlags_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.stripFlags(null));
    }

    @Test
    public void stripFlags_validInputs_correctResult() {
        assertEquals(StringUtil.stripFlags(""), "");
        assertEquals(StringUtil.stripFlags("words    words   more words"), "words words more words");
        assertEquals(StringUtil.stripFlags("words   -flag more    words -anotherflag"), "words more words");
        assertEquals(StringUtil.stripFlags("-flag -moreflags  -anotherflag"), "");
    }

    //---------------- Tests for removeExtraWhitespace --------------------------------------
    /*
     * Equivalence Partitions:
     * - null
     * - empty string
     * - strings with no extra spaces in between words
     * - strings with extra spaces in between words
     * - strings with other whitespace characters in between words
     */

    @Test
    public void removeExtraWhitespace_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.removeExtraWhitespace(null));
    }

    @Test
    public void removeExtraWhitespace_validInputs_correctResult() {
        assertEquals(StringUtil.removeExtraWhitespace(""), "");
        assertEquals(StringUtil.removeExtraWhitespace("words words more words"), "words words more words");
        assertEquals(StringUtil.removeExtraWhitespace("words    more    words       -flag"), "words more words -flag");
        assertEquals(StringUtil.removeExtraWhitespace("words \t \n words"), "words words");
    }

    //---------------- Tests for startsWithCommand --------------------------------------
    /*
     * Equivalence Partitions for sentence:
     * - null
     * - empty string
     * - sentence with extra spaces (leading or trailing or in between words)
     * - sentence with one word
     * - sentence with multiple words
     *
     * Equivalence Partitions for word:
     * - null
     * - empty string
     * - valid word
     *
     * Possible scenarios returning true:
     * - sentence starts with word and then the string ends
     * - sentence starts with word followed by a whitespace character
     * - ignoring leading spaces, sentence starts with word
     *
     * Possible scenarios returning false:
     * - sentence does not start with word
     * - sentence starts with word but followed by non-whitespace character
     *
     */

    @Test
    public void startsWithCommand_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.startsWithCommand(null, "word"));
        assertThrows(NullPointerException.class, () -> StringUtil.startsWithCommand("a a a a", null));
    }

    @Test
    public void startsWithCommand_validInputs_correctResult() {
        // Possible scenarios returning true
        assertTrue(StringUtil.startsWithCommand("", ""));
        assertTrue(StringUtil.startsWithCommand("   ls -contacts", "ls -contacts"));
        assertTrue(StringUtil.startsWithCommand("command argument1 argument2", "command"));
        assertTrue(StringUtil.startsWithCommand(" \n  command argument1 argument2", "command"));

        // Possible scenarios returning false
        assertFalse(StringUtil.startsWithCommand("sentence", "")); // no extra space
        assertFalse(StringUtil.startsWithCommand("", "command"));
        assertFalse(StringUtil.startsWithCommand("sentence", "command"));
        assertFalse(StringUtil.startsWithCommand("helpppp", "help")); // main use case for this method
        assertFalse(StringUtil.startsWithCommand("rmm 3", "rm")); // main use case for this method
    }
}
