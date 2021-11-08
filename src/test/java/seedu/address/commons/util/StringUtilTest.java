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


    //---------------- Tests for phrasesStartWithQuery --------------------------------------

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
     *   - matches middle phrases
     *   - partial matches
     *   - matches phrases even if case different
     *
     * Possible scenarios returning false:
     *   - query is a substring but not a prefix of the phrase (e.g. query: "bc" and sentence: "abc")
     *   - only query's first word matches phrase (e.g. query: "aaa bbb" and sentence: "bbb ccc")
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */
    @Test
    public void phrasesStartWithQuery_validInputs_returnsTrue() {
        //one word matches
        String sentence = "aaa bbb ccc";
        String query = "aaa";
        assertTrue(StringUtil.phrasesStartsWithQuery(sentence, query));
        //two word match
        String sentence2 = "aaa bbb ccc";
        String query2 = "bbb ccc";
        assertTrue(StringUtil.phrasesStartsWithQuery(sentence2, query2));
        //middle phrase match
        String sentence3 = "aaa bbb ccc ddd";
        String query3 = "bbb ccc";
        assertTrue(StringUtil.phrasesStartsWithQuery(sentence3, query3));
        //partial word match
        String sentence4 = "aaa bbb ccc ddd";
        String query4 = "bbb c";
        assertTrue(StringUtil.phrasesStartsWithQuery(sentence4, query4));
        //different case
        String sentence5 = "aaA bbb ccc";
        String query5 = "aaa bbB";
        assertTrue(StringUtil.phrasesStartsWithQuery(sentence5, query5));
    }

    @Test
    public void phrasesStartWithQuery_validInputs_returnsFalse() {
        // substring match but not a prefix
        String sentence = "abc def geh";
        String query = "ef";
        assertFalse(StringUtil.phrasesStartsWithQuery(sentence, query));
        // only first word of query matches phrase
        String sentence2 = "aaa bbb ccc";
        String query2 = "bbb ddd";
        assertFalse(StringUtil.phrasesStartsWithQuery(sentence2, query2));
    }

    @Test
    public void phrasesStartWithQuery_emptySentence_returnsFalse() {
        String sentence = "";
        String query = "aaa";
        assertFalse(StringUtil.phrasesStartsWithQuery(sentence, query));
    }

    @Test
    public void phrasesStartWithQuery_emptyQuery_throwsIllegalArgument() {
        String sentence = "aaa bbb ccc";
        String query = "";
        assertThrows(IllegalArgumentException.class, () -> StringUtil.phrasesStartsWithQuery(sentence, query));
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
