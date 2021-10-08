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
    private static final String INVALID_NAME = "Pudding^";
    private static final String INVALID_TAG = "#nice";
    private static final String INVALID_COUNT_1 = "sweet";
    private static final String INVALID_COUNT_2 = "-1";
    private static final String INVALID_Id = "abc";
    private static final String INVALID_Id_2 = "-1";
    private static final String INVALID_Id_3 = "123";

    private static final String VALID_NAME = "Pudding";
    private static final String VALID_TAG_1 = "nice";
    private static final String VALID_TAG_2 = "sweet";
    private static final String VALID_COUNT_1 = "2";
    private static final String VALID_COUNT_2 = "12";
    private static final String VALID_Id_1 = "223131";
    private static final String VALID_Id_2 = "002489";

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

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

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

    @Test
    public void parseCount_null_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCount(null));
    }

    @Test
    public void parseCount_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCount(INVALID_COUNT_1));
    }

    @Test
    public void parseCount_negativeNumber_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCount(INVALID_COUNT_2));
    }

    @Test
    public void parseCount_validValue_returnsCount() throws Exception {
        Integer expectedCount = Integer.parseInt(VALID_COUNT_1);
        Integer actualCount = ParserUtil.parseCount(VALID_COUNT_1);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void parseCount_validValue2_returnsCount() throws Exception {
        Integer expectedCount = Integer.parseInt(VALID_COUNT_2);
        Integer actualCount = ParserUtil.parseCount(VALID_COUNT_2);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void parseId_null_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId(null));
    }

    @Test
    public void parseId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_Id));
    }

    @Test
    public void parseId_negativeValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_Id_2));
    }

    @Test
    public void parseId_notSixDigits_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_Id_3));
    }

    @Test
    public void parseId_validId_returnsId() throws Exception {
        String expectedId = VALID_Id_1;
        String actualId = ParserUtil.parseId(VALID_Id_1);
        assertEquals(expectedId, actualId);
    }

    @Test
    public void parseId_validId2_returnsId() throws Exception {
        String expectedId = VALID_Id_2;
        String actualId = ParserUtil.parseId(VALID_Id_2);
        assertEquals(expectedId, actualId);
    }
}
