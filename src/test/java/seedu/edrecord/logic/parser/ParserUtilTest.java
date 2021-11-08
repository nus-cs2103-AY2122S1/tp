package seedu.edrecord.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edrecord.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.edrecord.testutil.Assert.assertThrows;
import static seedu.edrecord.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.assignment.Grade;
import seedu.edrecord.model.assignment.Score;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.Email;
import seedu.edrecord.model.person.Info;
import seedu.edrecord.model.person.Phone;
import seedu.edrecord.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_STATUS = "NOT_SUBMITTED";
    private static final String INVALID_SCORE = "-0.01";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "+12345678";
    private static final String VALID_INFO = "A friendly neighbour";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_STATUS_1 = "graded";
    private static final String VALID_STATUS_2 = "nOt sUbmIttED";
    private static final String VALID_STATUS_3 = "SUBMITTED";
    private static final String VALID_SCORE = "0";

    private static final String WHITESPACE = " \t\r\n";

    //=========== Parse Index ================================================================================

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
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    //=========== Parse Name =================================================================================

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

    //=========== Parse Phone ================================================================================

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    //=========== Parse Info =================================================================================

    @Test
    public void parseInfo_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInfo((String) null));
    }

    @Test
    public void parseInfo_validValueWithoutWhitespace_returnsInfo() throws Exception {
        Info expectedInfo = new Info(VALID_INFO);
        assertEquals(expectedInfo, ParserUtil.parseInfo(VALID_INFO));
    }

    @Test
    public void parseInfo_validValueWithWhitespace_returnsTrimmedInfo() throws Exception {
        String infoWithWhitespace = WHITESPACE + VALID_INFO + WHITESPACE;
        Info expectedInfo = new Info(VALID_INFO);
        assertEquals(expectedInfo, ParserUtil.parseInfo(infoWithWhitespace));
    }

    //=========== Parse Email ================================================================================

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    //=========== Parse Tag ==================================================================================

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

    //=========== Parse Score ================================================================================s

    @Test
    public void parseScore_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseScore(null));
    }

    @Test
    public void parseScore_validScore_returnsScore() throws Exception {
        Score expectedScore = new Score(VALID_SCORE);
        assertEquals(expectedScore, ParserUtil.parseScore(VALID_SCORE));
    }

    @Test
    public void parseScore_validValueWithWhitespace_returnsScore() throws Exception {
        String scoreWithWhitespace = WHITESPACE + VALID_SCORE + WHITESPACE;
        Score expectedScore = new Score(VALID_SCORE);
        assertEquals(expectedScore, ParserUtil.parseScore(scoreWithWhitespace));
    }

    @Test
    public void parseScore_invalidScore_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseScore(INVALID_SCORE));
    }

    //=========== Parse Grade Status =========================================================================s

    @Test
    public void parseStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStatus(null));
    }

    @Test
    public void parseStatus_validStatusLowerCase_returnsStatus() throws Exception {
        assertEquals(Grade.GradeStatus.GRADED, ParserUtil.parseStatus(VALID_STATUS_1));
    }

    @Test
    public void parseStatus_validStatusMixedCase_returnsStatus() throws Exception {
        assertEquals(Grade.GradeStatus.NOT_SUBMITTED, ParserUtil.parseStatus(VALID_STATUS_2));
    }

    @Test
    public void parseStatus_validStatusUpperCase_returnsStatus() throws Exception {
        assertEquals(Grade.GradeStatus.SUBMITTED, ParserUtil.parseStatus(VALID_STATUS_3));
    }

    @Test
    public void parseStatus_validValueWithWhitespace_returnsStatus() throws Exception {
        String statusWithWhitespace = WHITESPACE + VALID_STATUS_2 + WHITESPACE;
        assertEquals(Grade.GradeStatus.NOT_SUBMITTED, ParserUtil.parseStatus(statusWithWhitespace));
    }

    @Test
    public void parseStatus_invalidStatus_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(INVALID_STATUS));
    }
}
