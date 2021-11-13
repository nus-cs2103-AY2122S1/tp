package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClientId.CLIENTID_FIRST_CLIENT;
import static seedu.address.testutil.TypicalClientId.CLIENTID_SECOND_CLIENT;
import static seedu.address.testutil.TypicalClientId.CLIENTID_ZERO_CLIENT;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalClients.ELLE;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.Client;
import seedu.address.model.client.ClientId;

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

    //---------------- Tests for isNonNegativedInteger --------------------------------------

    @Test
    public void isNonNegativeInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonNegativeInteger("")); // Boundary value
        assertFalse(StringUtil.isNonNegativeInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonNegativeInteger("a"));
        assertFalse(StringUtil.isNonNegativeInteger("aaa"));

        // EP: zero
        assertTrue(StringUtil.isNonNegativeInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonNegativeInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonNegativeInteger("-1"));
        assertFalse(StringUtil.isNonNegativeInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonNegativeInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonNegativeInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonNegativeInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonNegativeInteger("1")); // Boundary value
        assertTrue(StringUtil.isNonNegativeInteger("10"));
    }

    @Test
    public void isNonNegativeDouble() {

        // EP: empty strings
        assertFalse(StringUtil.isNonNegativeDouble("")); // Boundary value
        assertFalse(StringUtil.isNonNegativeDouble("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonNegativeDouble("a"));
        assertFalse(StringUtil.isNonNegativeDouble("aaa"));

        // EP: zero
        assertTrue(StringUtil.isNonNegativeDouble("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonNegativeDouble("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonNegativeDouble("-1"));
        assertFalse(StringUtil.isNonNegativeDouble("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonNegativeDouble("1 0")); // Spaces in the middle

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonNegativeDouble("1")); // Boundary value
        assertTrue(StringUtil.isNonNegativeDouble("10"));
        assertTrue(StringUtil.isNonNegativeDouble("1.3"));
        assertTrue(StringUtil.isNonNegativeDouble("10.3"));
    }

    //---------------- Tests for isNumeric --------------------------------------

    @Test
    public void isNumeric() {

        // EP: empty strings
        assertFalse(StringUtil.isNumeric("")); // Boundary value
        assertFalse(StringUtil.isNumeric("  "));

        // EP: not a number
        assertFalse(StringUtil.isNumeric("a"));
        assertFalse(StringUtil.isNumeric("aaa"));

        // EP: zero
        assertTrue(StringUtil.isNumeric("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNumeric("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNumeric("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNumeric("1 0")); // Spaces in the middle

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNumeric("-1"));
        assertTrue(StringUtil.isNumeric("1")); // Boundary value
        assertTrue(StringUtil.isNumeric("10"));
        assertTrue(StringUtil.isNumeric("1.3"));
        assertTrue(StringUtil.isNumeric("10.3"));
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

    //---------------- Tests for containsStringIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsStringIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsStringIgnoreCase(null, "a"));
    }

    @Test
    public void containsStringIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsStringIgnoreCase("a", null));
    }

    @Test
    public void containsStringIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StringUtil.containsStringIgnoreCase("a", ""));
    }

    @Test
    public void containsIgnoreCase_validInputs_correctResult() {
        assertFalse(StringUtil.containsStringIgnoreCase("", "aaa"));
        assertFalse(StringUtil.containsStringIgnoreCase("abd", "g"));
        assertFalse(StringUtil.containsStringIgnoreCase("abd", " a"));

        assertTrue(StringUtil.containsStringIgnoreCase("abc", "a"));
        assertTrue(StringUtil.containsStringIgnoreCase("ab c", "c"));

        //match uppercase and lowercase
        assertTrue(StringUtil.containsStringIgnoreCase("abC", "C"));
        assertTrue(StringUtil.containsStringIgnoreCase("abfCa", "fCa"));

        // match for word with spaces in between
        assertTrue(StringUtil.containsStringIgnoreCase("b a a d", "a a"));

        // match multiple
        assertTrue(StringUtil.containsStringIgnoreCase("acadffac", "ac"));
    }

    //---------------- Tests for isValidDate --------------------------------------

    /*
     * Equivalence Partitions: null, valid date, invalid date in correct format, valid date in incorrect format,
     * random string, empty string
     */

    @Test
    public void isValidDate_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.isValidDate(null));
    }

    @Test
    public void isValidDate_validDate_returnsTrue() {
        assertTrue(StringUtil.isValidDate("18-10-2021"));
    }

    @Test
    public void isValidDate_validDateIncorrectFormat_returnsFalse() {
        assertFalse(StringUtil.isValidDate("2021-10-18"));
    }

    @Test
    public void isValidDate_invalidDate_returnsFalse() {
        assertFalse(StringUtil.isValidDate("18-13-2021"));
    }

    @Test
    public void isValidDate_randomString_returnsFalse() {
        assertFalse(StringUtil.isValidDate(" "));
    }

    @Test
    public void isValidDate_emptyString_returnsTrue() {
        assertTrue(StringUtil.isValidDate(""));
    }

    //---------------- Tests for isValidTime --------------------------------------

    /*
     * Equivalence Partitions: null, valid time, invalid time in correct format, valid time in incorrect format,
     * random string, empty string
     */

    @Test
    public void isValidTime_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.isValidTime(null));
    }

    @Test
    public void isValidTime_validTime_returnsTrue() {
        assertTrue(StringUtil.isValidTime("23:59"));
    }

    @Test
    public void isValidTime_validTimeIncorrectFormat_returnsFalse() {
        assertFalse(StringUtil.isValidDate("2359"));
    }

    @Test
    public void isValidTime_invalidTime_returnsFalse() {
        assertFalse(StringUtil.isValidDate("24:00"));
    }

    @Test
    public void isValidTime_randomString_returnsFalse() {
        assertFalse(StringUtil.isValidDate(" "));
    }

    @Test
    public void isValidTime_emptyString_returnsTrue() {
        assertTrue(StringUtil.isValidDate(""));
    }

    //---------------- Tests for isWithinLengthLimit --------------------------------------

    /*
     * EP for str: null, empty string,  valid Strings within limit, invalid Strings over limit
     *
     * EP for limit: negative, 0, positive
     */

    @Test
    public void isWithinLengthLimit_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.isWithinLengthLimit(null, 10));
    }

    @Test
    public void isWithinLengthLimit_emptyString_returnsTrue() {
        assertTrue(StringUtil.isWithinLengthLimit("", 10));
    }

    @Test
    public void isWithinStandardLimit_exactlyWithinLimit_returnsTrue() {
        assertTrue(StringUtil.isWithinLengthLimit(
            "0123456789" + "0123456789" + "0123456789", 30
        ));
    }

    @Test
    public void isWithinStandardLimit_exactlyWithinLongerLimit_returnsTrue() {
        assertTrue(StringUtil.isWithinLengthLimit(
            "0123456789" + "0123456789" + "0123456789" + "0123456789" + "0123456789" + "0123456789"
                + "0123456789" + "0123456789" + "0123456789" + "0123456789", 100
        ));
    }

    @Test
    public void isWithinLengthLimit_exceedLimit_returnsFalse() {
        assertFalse(StringUtil.isWithinLengthLimit(
            "0123456789" + "0123456789" + "0123456789" + "1", 30
        ));
    }

    @Test
    public void isWithinLengthLimit_limitIsZero_returnsTrue() {
        assertTrue(StringUtil.isWithinLengthLimit("", 0));
    }

    @Test
    public void isWithinLengthLimit_positiveLimit_returnsTrue() {
        assertTrue(StringUtil.isWithinLengthLimit("hi", 10));
    }

    @Test
    public void isWithinLengthLimit_negativeLimit_returnsFalse() {
        assertFalse(StringUtil.isWithinLengthLimit("", -10));
    }



    //---------------- Tests for isValidCurrencyValue --------------------------------------
    /*
     * Equivalence Partitions: null, valid currency value,
     * invalid currency value in correct format, valid currency value in incorrect format,
     * random string, empty string
     */

    @Test
    public void isValidCurrencyValue_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.isValidCurrencyValue(null));
    }

    @Test
    public void isValidCurrencyValue_validCurrencyValue_returnsTrue() {
        assertTrue(StringUtil.isValidCurrencyValue("369"));
        assertTrue(StringUtil.isValidCurrencyValue("369.69"));
        assertTrue(StringUtil.isValidCurrencyValue("50,0000.963")); // with comma
    }

    @Test
    public void isValidCurrencyValue_validCurrencyValueIncorrectFormat_returnsFalse() {
        assertFalse(StringUtil.isValidCurrencyValue("$369.69")); // with dollar symbol
    }

    @Test
    public void isValidCurrencyValue_invalidCurrencyValue_returnsFalse() {
        assertFalse(StringUtil.isValidCurrencyValue("invalid value"));
    }

    @Test
    public void isValidCurrencyValue_randomString_returnsFalse() {
        assertFalse(StringUtil.isValidCurrencyValue(" "));
    }

    @Test
    public void isValidCurrencyValue_emptyString_returnsFalse() {
        assertFalse(StringUtil.isValidCurrencyValue(""));
    }

    //---------------- Tests for parseToLocalDate --------------------------------------

    /*
     * Equivalence Partitions: empty string, valid date
     */

    @Test
    public void parseToLocalDate_emptyString_returnsNull() {
        assertNull(StringUtil.parseToLocalDate(""));
    }

    @Test
    public void parseToLocalDate_validString_returnsCorrectLocalDate() {
        assertEquals(LocalDate.of(2021, 10, 18), StringUtil.parseToLocalDate("18-10-2021"));
    }

    //---------------- Tests for parseToLocalTime --------------------------------------

    /*
     * Equivalence Partitions: empty string, valid date
     */

    @Test
    public void parseToLocalTime_emptyString_returnsNull() {
        assertNull(StringUtil.parseToLocalTime(""));
    }

    @Test
    public void parseToLocalTime_validString_returnsCorrectLocalDate() {
        assertEquals(LocalTime.of(20, 20), StringUtil.parseToLocalTime("20:20"));
    }

    //---------------- Tests for convertEmptyStringIfNull --------------------------------------

    /*
     * Equivalence Partitions: null, empty string, non-empty string
     */

    @Test
    public void convertEmptyStringIfNull_null_returnsEmptyString() {
        assertEquals(StringUtil.convertEmptyStringIfNull(null), "");
    }

    @Test
    public void convertEmptyStringIfNull_emptyString_returnsUnchangedString() {
        assertEquals(StringUtil.convertEmptyStringIfNull(""), "");
    }

    @Test
    public void convertEmptyStringIfNull_nonEmptyString_returnsUnchangedString() {
        assertEquals(StringUtil.convertEmptyStringIfNull("test 123"), "test 123");
    }

    //---------------- Tests for removeLeadingZeros --------------------------------------

    @Test
    public void removeLeadingZeros_null_returnsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.removeLeadingZeros(null));
    }

    @Test
    public void removeLeadingZeros_nonEmptyString_returnsStringWithLeadingZerosRemoved() {
        assertEquals(StringUtil.removeLeadingZeros("09"), "9");
    }

    @Test
    public void convertEmptyStringIfNull_emptyString_returnsEmptyString() {
        assertEquals(StringUtil.convertEmptyStringIfNull(""), "");
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

    //---------------- Tests for getUnformattedNumber --------------------------------------

    /*
     * Equivalence Partitions: number string with comma, number string without comma, non-numeric string
     */

    @Test
    public void getUnformattedNumber_withCommaGiven() {
        assertEquals("60000", StringUtil.getUnformattedNumber("60,000"));
    }

    @Test
    public void getUnformattedNumber_withoutCommaGiven() {
        assertEquals("60000", StringUtil.getUnformattedNumber("60000"));
    }

    @Test
    public void getUnformattedNumber_nonNumericGiven_returnsEmptyString() {
        assertTrue(StringUtil.getUnformattedNumber("test").isEmpty());
    }

    //---------------- Tests for joinListToString --------------------------------------

    /**
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void joinListToString_listNull_throwNullPointersException() {
        assertThrows(NullPointerException.class, () -> StringUtil.joinListToString(null, ""));
    }

    @Test
    public void joinListToString_delimiterNull_throwNullPointersException() {
        assertThrows(NullPointerException.class, () -> StringUtil.joinListToString(List.of(""), null));
    }

    @Test
    public void joinListToString_validInputs_correctResult() {
        List<Client> clientList = List.of(ALICE, BENSON, ELLE);
        String clientDelimiter = "\n";
        String clientResult = ALICE + clientDelimiter + BENSON + clientDelimiter + ELLE;
        assertEquals(StringUtil.joinListToString(clientList, clientDelimiter), clientResult);

        List<ClientId> clientIdList = List.of(CLIENTID_ZERO_CLIENT, CLIENTID_FIRST_CLIENT, CLIENTID_SECOND_CLIENT);
        String clientIdDelimiter = ", ";
        String clientIdResult = CLIENTID_ZERO_CLIENT + clientIdDelimiter + CLIENTID_FIRST_CLIENT
            + clientIdDelimiter + CLIENTID_SECOND_CLIENT;
        assertEquals(StringUtil.joinListToString(clientIdList, clientIdDelimiter), clientIdResult);

    }

    //---------------- Tests for joinListToString --------------------------------------

    /**
     * Equivalence Partitions: null, valid with suffix, valid without suffix
     */

    @Test
    public void getStringWithoutSuffix_listNull_throwNullPointersException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getStringWithoutSuffix(null, "wordword"));
    }

    @Test
    public void getStringWithoutSuffix_delimiterNull_throwNullPointersException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getStringWithoutSuffix("wordword", null));
    }

    @Test
    public void getStringWithoutSuffix_validInputs_correctResult() {
        String input = "addressbook.json";
        String prefix = ".json";
        String result = StringUtil.getStringWithoutSuffix(input, prefix);
        String expectedResult = "addressbook";
        assertEquals(result, expectedResult);
    }

    @Test
    public void getStringWithoutSuffix_validInputs_noSuffix() {
        String input = "addressbook.jso";
        String prefix = ".json";
        String result = StringUtil.getStringWithoutSuffix(input, prefix);
        assertEquals(result, input);
    }

    //---------------- Tests for transformEmptyRepresentation --------------------------------------

    /**
     * Equivalence Partitions: null, non-empty, empty
     */

    @Test
    public void transformEmptyRepresentation_nullInput_returnEmptyRepresentation() {
        assertEquals("-", StringUtil.transformEmptyRepresentation(null));
    }

    @Test
    public void transformEmptyRepresentation_emptyString_returnEmptyRepresentation() {
        assertEquals("-", StringUtil.transformEmptyRepresentation("")); // boundary value
        assertEquals("-", StringUtil.transformEmptyRepresentation("   "));
    }

    @Test
    public void transformEmptyRepresentation_nonEmptyString_returnInput() {
        assertEquals("a", StringUtil.transformEmptyRepresentation("a"));
    }
}
