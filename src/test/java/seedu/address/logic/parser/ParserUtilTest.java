package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessoncode.LessonCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_REMARK = "Ãgent John";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_REMARK = "international student #2";

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
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
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

    @Test
    public void parseLessonCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLessonCode(null));
    }

    @Test
    public void parseLessonCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLessonCode(INVALID_TAG));
    }

    @Test
    public void parseLessonCode_validValueWithoutWhitespace_returnsTag() throws Exception {
        LessonCode expectedLessonCode = new LessonCode(VALID_TAG_1);
        assertEquals(expectedLessonCode, ParserUtil.parseLessonCode(VALID_TAG_1));
    }

    @Test
    public void parseLessonCode_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        LessonCode expectedLessonCode = new LessonCode(VALID_TAG_1);
        assertEquals(expectedLessonCode, ParserUtil.parseLessonCode(tagWithWhitespace));
    }

    @Test
    public void parseLessonCodes_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLessonCodes(null));
    }

    @Test
    public void parseLessonCodes_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLessonCodes(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseLessonCodes_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseLessonCodes(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseLessonCodes_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<LessonCode> actualLessonCodeSet = ParserUtil.parseLessonCodes(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<LessonCode> expectedLessonCodeSet = new HashSet<LessonCode>(
                Arrays.asList(new LessonCode(VALID_TAG_1), new LessonCode(VALID_TAG_2))
        );

        assertEquals(expectedLessonCodeSet, actualLessonCodeSet);
    }

    @Test
    public void parseRemark_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRemark((String) null));
    }

    @Test
    public void parseRemark_validValueWithoutWhitespace_returnsRemark() throws Exception {
        Remark expectedRemark = new Remark(VALID_REMARK);
        assertEquals(expectedRemark, ParserUtil.parseRemark(VALID_REMARK));
    }

    @Test
    public void parseRemark_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRemark(INVALID_REMARK));
    }

}
