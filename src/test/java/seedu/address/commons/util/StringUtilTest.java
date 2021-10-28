package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.Name;

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
    public void containsMultipleWord_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsMultipleWord("typical sentence", null));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "  "));
    }
    @Test
    public void containsMultipleWord_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsMultipleWord("typical sentence", "  "));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase(null, "abc"));
    }
    @Test
    public void containsMultipleWord_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsMultipleWord(null, "abc"));
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
    public void equalArray_validInputs_correctResult() {
        //one word matches
        String [] first = {"aaa", "bbb", "Ccc"};
        String [] second = {"aaa"};
        assertTrue(StringUtil.equalArray(first, second));
        //complete match
        String [] first2 = {"aaa", "bbb", "Ccc"};
        String [] second2 = {"aaa", "bbb", "Ccc"};
        assertTrue(StringUtil.equalArray(first2, second2));
        //different case
        String [] first3 = {"aaa", "bbb", "Ccc"};
        String [] second3 = {"ccc"};
        assertTrue(StringUtil.equalArray(first3, second3));
        //empty string
        String [] first4 = {"aaa", "bbb", "Ccc"};
        String [] second4 = {};
        assertFalse(StringUtil.equalArray(first4, second4));
        //partial string
        String [] first5 = {"aaa", "bbb", "Ccc"};
        String [] second5 = {"aa"};
        assertFalse(StringUtil.equalArray(first5, second5));
    }

    @Test
    public void equalArrayElements_validInputs_correctResult() {
        //wrong index
        String [] first = {"aaa", "bbb", "Ccc"};
        String [] second = {"Ccc"};
        assertFalse(StringUtil.equalArrayElements(first, second, 1));
        //correct index
        String [] first2 = {"aaa", "bbb", "Ccc"};
        String [] second2 = {"Ccc"};
        assertTrue(StringUtil.equalArrayElements(first2, second2, 2));
        //different case
        String [] first3 = {"aaa", "bbb", "Ccc"};
        String [] second3 = {"ccc"};
        assertTrue(StringUtil.equalArrayElements(first3, second3, 2));
        //index out of bounds
        String [] first4 = {"aaa", "bbb", "Ccc"};
        String [] second4 = {};
        assertFalse(StringUtil.equalArrayElements(first4, second4, 3));
        //partial string
        String [] first5 = {"aaa", "bbb", "Ccc"};
        String [] second5 = {"aa"};
        assertFalse(StringUtil.equalArrayElements(first5, second5, 1));
    }

    @Test
    public void containsWordIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsWordIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsWordIgnoreCase("    ", "123"));

        // Matches a partial word only
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


    @Test
    public void containsMultipleWord_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsMultipleWord("", "abc")); // Boundary case
        assertFalse(StringUtil.containsMultipleWord("    ", "123"));

        // Matches a partial word only
        assertFalse(StringUtil.containsMultipleWord("aaa bbb ccc", "bb")); // Sentence word bigger than query word
        assertFalse(StringUtil.containsMultipleWord("aaa bbb ccc", "bbbb")); // Query word bigger than sentence word

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsMultipleWord("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil.containsMultipleWord("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsMultipleWord("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil.containsMultipleWord("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsMultipleWord("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsMultipleWord("AAA bBb ccc  bbb", "bbB"));
        assertTrue(StringUtil.containsMultipleWord("AAA bBb ccc  bbb", "bbB ccc"));
        assertTrue(StringUtil.containsMultipleWord("AAA bBb ccc  bbb", "bbB ccc bbb"));
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
    public void generateRandomString_normalGeneration_stringIsValidName() {
        String generatedString = StringUtil.generateRandomString();
        assertTrue(Name.isValidName(generatedString));
    }
}
