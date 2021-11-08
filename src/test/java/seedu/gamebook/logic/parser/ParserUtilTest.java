package seedu.gamebook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.gamebook.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.gamebook.testutil.Assert.assertThrows;
import static seedu.gamebook.testutil.TypicalIndexes.INDEX_FIRST_GAMEENTRY;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

// import seedu.gamebook.logic.parser.exceptions.ParseException;
import seedu.gamebook.model.gameentry.DatePlayed;
import seedu.gamebook.model.gameentry.Duration;
import seedu.gamebook.model.gameentry.EndAmount;
import seedu.gamebook.model.gameentry.GameType;
import seedu.gamebook.model.gameentry.Location;
import seedu.gamebook.model.gameentry.StartAmount;
import seedu.gamebook.model.tag.Tag;

public class ParserUtilTest {

    private static final String INVALID_STARTAMOUNT = "abc";
    private static final String INVALID_ENDAMOUNT = "abc";
    private static final String INVALID_DATE = "2021-01 10:00";
    private static final String INVALID_DURATION = "abc";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_GAMETYPE = "Poker";
    private static final String VALID_STARTAMOUNT = "0.0";
    private static final String VALID_ENDAMOUNT = "100.0";
    private static final String VALID_DATE = "2021-01-01 10:00";
    private static final String VALID_DURATION = "10";
    private static final String VALID_LOCATION = "Sentosa";
    private static final String VALID_TAG_1 = "lucky";
    private static final String VALID_TAG_2 = "drunk";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() {
        // No whitespaces
        assertEquals(INDEX_FIRST_GAMEENTRY, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_GAMEENTRY, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseGameType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGameType((String) null));
    }


    @Test
    public void parseGameType_validValueWithoutWhitespace_returnsGameType() throws Exception {
        assertEquals(new GameType(VALID_GAMETYPE), ParserUtil.parseGameType(VALID_GAMETYPE));
    }

    @Test
    public void parseGameType_validValueWithWhitespace_returnsTrimmedGameType() {
        String gameTypeWithWhitespace = WHITESPACE + VALID_GAMETYPE + WHITESPACE;
        assertEquals(new GameType(VALID_GAMETYPE), ParserUtil.parseGameType(gameTypeWithWhitespace));
    }

    @Test
    public void parseStartAmount_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStartAmount((String) null));
    }

    @Test
    public void parseStartAmount_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseStartAmount(INVALID_STARTAMOUNT));
    }

    @Test
    public void parseStartAmount_validValueWithoutWhitespace_returnsStartAmount() throws Exception {
        assertEquals(new StartAmount(VALID_STARTAMOUNT), ParserUtil.parseStartAmount(VALID_STARTAMOUNT));
    }

    @Test
    public void parseStartAmount_validValueWithWhitespace_returnsTrimmedStartAmount() throws Exception {
        String startAmountWithWhitespace = WHITESPACE + VALID_STARTAMOUNT + WHITESPACE;
        assertEquals(new StartAmount(VALID_STARTAMOUNT), ParserUtil.parseStartAmount(startAmountWithWhitespace));
    }

    @Test
    public void parseEndAmount_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEndAmount((String) null));
    }

    @Test
    public void parseEndAmount_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseEndAmount(INVALID_ENDAMOUNT));
    }

    @Test
    public void parseEndAmount_validValueWithoutWhitespace_returnsEndAmount() throws Exception {
        assertEquals(new EndAmount(VALID_ENDAMOUNT), ParserUtil.parseEndAmount(VALID_ENDAMOUNT));
    }

    @Test
    public void parseEndAmount_validValueWithWhitespace_returnsTrimmedEndAmount() throws Exception {
        String endAmountWithWhitespace = WHITESPACE + VALID_ENDAMOUNT + WHITESPACE;
        assertEquals(new EndAmount(VALID_ENDAMOUNT), ParserUtil.parseEndAmount(endAmountWithWhitespace));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseDate_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        DatePlayed expectedDate = new DatePlayed(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        DatePlayed expectedDate = new DatePlayed(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhitespace));
    }

    @Test
    public void parseDuration_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDuration((String) null));
    }

    @Test
    public void parseDuration_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseDuration(INVALID_DURATION));
    }

    @Test
    public void parseDuration_validValueWithoutWhitespace_returnsDuration() throws Exception {
        assertEquals(new Duration(VALID_DURATION), ParserUtil.parseDuration(VALID_DURATION));
    }

    @Test
    public void parseDuration_validValueWithWhitespace_returnsTrimmedDuration() throws Exception {
        String durationWithWhitespace = WHITESPACE + VALID_DURATION + WHITESPACE;
        assertEquals(new Duration(durationWithWhitespace), ParserUtil.parseDuration(durationWithWhitespace));
    }

    @Test
    public void parseLocation_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLocation((String) null));
    }


    @Test
    public void parseLocation_validValueWithoutWhitespace_returnsLocation() throws Exception {
        assertEquals(new Location(VALID_LOCATION), ParserUtil.parseLocation(VALID_LOCATION));
    }

    @Test
    public void parseLocation_validValueWithWhitespace_returnsTrimmedLocation() throws Exception {
        String locationWithWhitespace = WHITESPACE + VALID_LOCATION + WHITESPACE;
        assertEquals(new Location(VALID_LOCATION), ParserUtil.parseLocation(locationWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseTag(INVALID_TAG));
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
        String nullString = null;
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(nullString));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil
                .parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
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
}
