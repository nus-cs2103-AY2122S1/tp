package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Name;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME_CARET = "Pudding^";
    private static final String INVALID_NAME_DOLLAR = "$Pudding";
    private static final String INVALID_TAG = "#nice";
    private static final String INVALID_COUNT_ZERO = "0";
    private static final String INVALID_COUNT_FORMAT = "sweet";
    private static final String INVALID_COUNT_NEGATIVE = "-1";
    private static final String INVALID_COUNT_FLOAT = "12.2";
    private static final String INVALID_ID_FORMAT = "abc";
    private static final String INVALID_ID_NEGATIVE = "-1";
    private static final String INVALID_ID_LONGER = "1232343";
    private static final String INVALID_PRICE_FORMAT = "abc";
    private static final String INVALID_PRICE_NEGATIVE = "-1";
    private static final String INVALID_PRICE_OVERFLOW = "99999999.1";

    private static final String VALID_NAME = "Pudding";
    private static final String VALID_NAME_NUMERIC = "100";
    private static final String VALID_TAG_1 = "nice";
    private static final String VALID_TAG_2 = "sweet";
    private static final String VALID_ID_1 = "123456";
    private static final String VALID_ID_2 = "123";
    private static final String VALID_PRICE_1 = "1.21";
    private static final String VALID_PRICE_2 = "12.2121"; // Should be rounded to 2 decimal places

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_ITEM, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_ITEM, ParserUtil.parseIndex("  1  "));
    }

    /* Tests for parsing name:
       Equivalence Partitions:
            - null
            - Alphabets only
            - Numbers only
            - Alphabets and numbers
            - With special characters
            - With whitespace
     */
    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        // Special characters - "^"
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME_CARET));
        // Special characters - "$"
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME_DOLLAR));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        // Alphabets only
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
        // Numbers only
        expectedName = new Name(VALID_NAME_NUMERIC);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME_NUMERIC));
        // Alphabets and numbers
        expectedName = new Name(VALID_NAME + VALID_NAME_NUMERIC);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME + VALID_NAME_NUMERIC));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    /* Tests for parsing tag:
   Equivalence Partitions:
        - null
        - Alphanumeric characters
        - With special characters
        - With whitespace in between
    */
    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTag_whitespaceBetween_returnsTrimmedTag() throws Exception {
        String invalidTag = VALID_TAG_1 + WHITESPACE + VALID_TAG_1;
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(invalidTag));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    /* Tests for parsing count:
       Equivalence Partitions:
            - null
            - Integers within range [1, 999999]
            - 0
            - Negative integers
            - Large integers (> INT_MAX)
            - Not an integer
            - Not a number
     */
    @Test
    public void parseCount_null_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCount(null));
    }

    @Test
    public void parseCount_invalidValue_throwsParseException() {
        // Count == 0
        assertThrows(ParseException.class, () -> ParserUtil.parseCount(INVALID_COUNT_ZERO));
        // Negative integer
        assertThrows(ParseException.class, () -> ParserUtil.parseCount(INVALID_COUNT_NEGATIVE));
        // Large integer
        String largeString = String.format("%d", 1000000);
        assertThrows(ParseException.class, () -> ParserUtil.parseCount(largeString));
        // Not an integer
        assertThrows(ParseException.class, () -> ParserUtil.parseCount(INVALID_COUNT_FLOAT));
        // Not a number
        assertThrows(ParseException.class, () -> ParserUtil.parseCount(INVALID_COUNT_FORMAT));
    }

    @Test
    public void parseCount_validValue_returnsCount() throws Exception {
        // Tests the lower boundary of the valid range
        Integer expectedCount = ParserUtil.parseCount("1");
        assertEquals(expectedCount, 1);

        // Tests the upper boundary of the valid range
        String maxString = String.format("%d", 999999);
        expectedCount = ParserUtil.parseCount(maxString);
        assertEquals(expectedCount, 999999);
    }

    /* Tests for parsing id:
    Equivalence Partitions:
         - null
         - Integers within range [0, 999,999]
         - Negative integers
         - Large integers (> 6 digits)
         - Not an integer
     */

    @Test
    public void parseId_null_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId(null));
    }

    @Test
    public void parseId_invalidValue_throwsParseException() {
        // Negative integer
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_ID_NEGATIVE));
        // Large integer
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_ID_LONGER));
        // Not an integer
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_ID_FORMAT));
    }

    @Test
    public void parseId_validId_returnsId() throws Exception {
        // Tests the lower boundary of the valid range
        Integer expectedId = ParserUtil.parseId("000000");
        assertEquals(expectedId, 0);

        // Tests a middle value of the valid range
        expectedId = ParserUtil.parseId(VALID_ID_1);
        assertEquals(expectedId, Integer.parseInt(VALID_ID_1));

        // Tests the upper boundary of the valid range
        expectedId = ParserUtil.parseId("999999");
        assertEquals(expectedId, 999999);
    }

    @Test
    public void parseId_validIdLessThan6digits_returnsId() throws Exception {
        Integer expectedId = Integer.parseInt(VALID_ID_2);
        Integer actualId = ParserUtil.parseId(VALID_ID_2);
        assertEquals(expectedId, actualId);
    }

    /* Tests for parsing prices:
    Equivalence Partitions:
        - null
        - Values within range [0, 9,999,999]
        - Values within range but with more than 2 d.p.
        - Negative values
        - Large values
    */

    @Test
    public void parsePrice_validPrices_returnsPrice() throws Exception {
        // 2 decimal places
        double expectedPrice = Double.parseDouble(VALID_PRICE_1);
        double actualPrice = ParserUtil.parsePrice(VALID_PRICE_1);
        assertEquals(expectedPrice, actualPrice);

        // Extra decimal places
        expectedPrice = Math.round(Double.parseDouble(VALID_PRICE_2) * 100) / 100.0;
        actualPrice = ParserUtil.parsePrice(VALID_PRICE_2);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void parsePrice_negativePrice_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE_NEGATIVE));
    }

    @Test
    public void parsePrice_largePrice_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE_OVERFLOW));
    }

    @Test
    public void parsePrice_notNumbers_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE_FORMAT));
    }
}
