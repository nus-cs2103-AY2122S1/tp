package seedu.anilist.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.testutil.Assert.assertThrows;

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
        assertThrows(NullPointerException.class, () -> StringUtil.containsPhraseIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Phrase parameter cannot be empty", ()
            -> StringUtil.containsPhraseIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsPhraseIgnoreCase(null, "abc"));
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
    public void containsPhraseIgnoreCase_emptyString_returnsFalse() {
        assertFalse(StringUtil.containsPhraseIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsPhraseIgnoreCase("    ", "123"));
    }

    @Test
    public void containsPhraseIgnoreCase_matchPhrase_returnsTrue() {
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa bbb ccc", "bb"));
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsPhraseIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil.containsPhraseIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa bbb ccc", "  ccc  "));
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa bbb ccc", "bBb Ccc  "));
    }

    @Test
    public void containsPhraseIgnoreCase_doesNotMatchPhrase_returnsFalse() {
        assertFalse(StringUtil.containsPhraseIgnoreCase("Aaa", "aab"));
        assertFalse(StringUtil.containsPhraseIgnoreCase("aaa bbb ccc", "bbbc"));
        assertFalse(StringUtil.containsPhraseIgnoreCase("aaa bBb ccc", "Bb b"));
        assertFalse(StringUtil.containsPhraseIgnoreCase("  AAA   bBb   ccc  ", "AAA bBb"));
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

    @Test
    public void isIntegerInRange() {
        // EP: Within Range
        assertTrue(StringUtil.isIntegerInRange("0", -1, 1));
        assertTrue(StringUtil.isIntegerInRange("-1", -1, 1));
        assertTrue(StringUtil.isIntegerInRange("1", -1, 1));

        // EP: Out of Range
        assertFalse(StringUtil.isIntegerInRange("-2", -1, 1));
        assertFalse(StringUtil.isIntegerInRange("2", -1, 1));

        // EP: Integer Overflow and Integer
        assertFalse(StringUtil.isIntegerInRange("-9999999999999999999", -1, 1));
        assertFalse(StringUtil.isIntegerInRange("9999999999999999999", -1, 1));

        // EP: not a number
        assertFalse(StringUtil.isIntegerInRange("a", -1, 1));
        assertFalse(StringUtil.isIntegerInRange("aaa", -1, 1));

        // EP: zero as prefix
        assertTrue(StringUtil.isIntegerInRange("01", -1, 1));

        // EP: signed numbers
        assertTrue(StringUtil.isIntegerInRange("-1", -1, 1));
        assertFalse(StringUtil.isIntegerInRange("+1", -1, 1));

        // EP: numbers with white space
        assertFalse(StringUtil.isIntegerInRange(" 10 ", -1, 1)); // Leading/trailing spaces
        assertFalse(StringUtil.isIntegerInRange("1 0", -1, 1)); // Spaces in the middle
    }

}
