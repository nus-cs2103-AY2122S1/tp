package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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

        // Matches a partial word only
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger than query word
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
    public void containsWordIgnoreCase_emptySentence_correctOutput() {
        boolean contains = StringUtil.containsWordIgnoreCase(" ", "empty");
        assertFalse(contains);
    }

    @Test
    public void getInt_emptyString_correctOutput() {
        Integer i = StringUtil.getInt(" ");
        assertNull(i);
    }

    @Test
    public void getInt_invalidString_correctOutput() {
        Integer i = StringUtil.getInt("ab");
        assertNull(i);
    }

    @Test
    public void getInt_validString_correctOutput() {
        Integer i = StringUtil.getInt("2");
        assertTrue(i == 2);
    }

    @Test
    public void getInt_nullInput_nullPointerException() {
        boolean nullPointerException = true;
        try {
            Integer i = StringUtil.getInt(null);
            nullPointerException = false;
        } catch (NullPointerException e) {
            assertTrue(nullPointerException);
        }
    }

    @Test
    public void isJson_validFileName_correctOutput() {
        boolean validFileName = StringUtil.isJson("file.json");
        assertTrue(validFileName);
    }

    @Test
    public void isJson_invalidFileName_correctOutput() {
        boolean validFileName = StringUtil.isJson("file.csv");
        assertFalse(validFileName);
    }

    @Test
    public void isJson_nullInput_nullPointerException() {
        boolean nullPointerException = true;
        try {
            boolean validFileName = StringUtil.isJson(null);
            nullPointerException = false;
        } catch (NullPointerException e) {
            assertTrue(nullPointerException);
        }
    }

    @Test
    public void isCsv_validFileName_correctOutput() {
        boolean validFileName = StringUtil.isCsv("file.csv");
        assertTrue(validFileName);
    }

    @Test
    public void isCsv_invalidFileName_correctOutput() {
        boolean validFileName = StringUtil.isCsv("file.json");
        assertFalse(validFileName);
    }

    @Test
    public void isCsv_nullInput_nullPointerException() {
        boolean nullPointerException = true;
        try {
            boolean validFileName = StringUtil.isCsv(null);
            nullPointerException = false;
        } catch (NullPointerException e) {
            assertTrue(nullPointerException);
        }
    }

    @Test
    public void clean_aNormalString_correctOutput() {
        String s = StringUtil.clean("jai2501");
        assertTrue(s.equals("jai2501"));
    }

    @Test
    public void clean_aStringWithNewLineCharacter_correctOutput() {
        String s = StringUtil.clean("Hello\nWorld");
        assertTrue(s.equals("HelloWorld"));
    }

    @Test
    public void clean_nullInput_nullPointerException() {
        boolean nullPointerException = true;
        try {
            String s = StringUtil.clean(null);
            nullPointerException = false;
        } catch (NullPointerException e) {
            assertTrue(nullPointerException);
        }
    }

    @Test
    public void clean_aStringContainingTheStringToBeRemoved_correctOutput() {
        String s = StringUtil.clean("Hello World", "World");
        assertTrue(s.equals("Hello"));
    }

    @Test
    public void clean_aStringNotContainingTheStringToBeRemoved_correctOutput() {
        String s = StringUtil.clean("Hello World", "Boy");
        assertTrue(s.equals("Hello World"));
    }

    @Test
    public void clean_twoNullInputs_nullPointerException() {
        boolean nullPointerException = true;
        try {
            String s = StringUtil.clean(null, null);
            nullPointerException = false;
        } catch (NullPointerException e) {
            assertTrue(nullPointerException);
        }
    }

    @Test
    public void clean_firstNullSecondString_nullPointerException() {
        boolean nullPointerException = true;
        try {
            String s = StringUtil.clean(null, "null");
            nullPointerException = false;
        } catch (NullPointerException e) {
            assertTrue(nullPointerException);
        }
    }

    @Test
    public void clean_firstStringSecondNull_nullPointerException() {
        boolean nullPointerException = true;
        try {
            String s = StringUtil.clean("null", null);
            nullPointerException = false;
        } catch (NullPointerException e) {
            assertTrue(nullPointerException);
        }
    }
}
