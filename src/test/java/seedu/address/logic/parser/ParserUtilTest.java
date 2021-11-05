package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.ClassCode;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialclass.Schedule;
import seedu.address.model.tutorialgroup.GroupNumber;
import seedu.address.model.tutorialgroup.GroupType;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_GROUP_NUMBER = "a";
    private static final String INVALID_GROUP_TYPE = "OP3";
    private static final String INVALID_CLASSCODE = "T01";
    private static final String INVALID_SCHEDULE = " ";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_CLASSCODE = "G01";
    private static final String VALID_SCHEDULE = "Monday 10:00am to 12:00pm, Thursday 10:00am to 12:00pm";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_GROUP_NUMBER = "1";
    private static final String VALID_GROUP_TYPE = "OP1";

    private static final String WHITESPACE = " \t\r\n";
    private static final String DEFAULT_CLASSCODE = "G00";

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
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
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
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
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
    public void parseClassCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClassCode((String) null));
    }

    @Test
    public void parseClassCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_CLASSCODE));
    }

    @Test
    public void parseClassCode_defaultValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(DEFAULT_CLASSCODE));
    }

    @Test
    public void parseClassCode_validValueWithoutWhitespace_returnsClassCode() throws Exception {
        ClassCode expectedClassCode = new ClassCode(VALID_CLASSCODE);
        assertEquals(expectedClassCode, ParserUtil.parseClassCode(VALID_CLASSCODE));
    }

    @Test
    public void parseClassCode_validValueWithWhitespace_returnsTrimmedClassCode() throws Exception {
        String classCodeWithWhiteSpace = WHITESPACE + VALID_CLASSCODE + WHITESPACE;
        ClassCode expectedClassCode = new ClassCode(VALID_CLASSCODE);
        assertEquals(expectedClassCode, ParserUtil.parseClassCode(classCodeWithWhiteSpace));
    }

    @Test
    public void parseSchedule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSchedule(null));
    }

    @Test
    public void parseSchedule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSchedule(INVALID_SCHEDULE));
    }

    @Test
    public void parseSchedule_validValueWithWhitespace_returnsTrimmedSchedule() throws Exception {
        String scheduleWithWhiteSpace = WHITESPACE + VALID_SCHEDULE + WHITESPACE;
        Schedule expectedSchedule = new Schedule(VALID_SCHEDULE);
        assertEquals(expectedSchedule, ParserUtil.parseSchedule(scheduleWithWhiteSpace));
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
    public void parseGroupNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroupNumber((String) null));
    }

    @Test
    public void parseGroupNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroupNumber(INVALID_GROUP_NUMBER));
    }

    @Test
    public void parseGroupNumber_validValueWithoutWhitespace_returnsGroupNumber() throws Exception {
        GroupNumber expectedGroupNumber = new GroupNumber(VALID_GROUP_NUMBER);
        assertEquals(expectedGroupNumber, ParserUtil.parseGroupNumber(VALID_GROUP_NUMBER));
    }

    @Test
    public void parseGroupNumber_validValueWithWhitespace_returnsTrimmedGroupNumber() throws Exception {
        String groupNumberWithWhitespace = WHITESPACE + VALID_GROUP_NUMBER + WHITESPACE;
        GroupNumber expectedGroupNumber = new GroupNumber(VALID_GROUP_NUMBER);
        assertEquals(expectedGroupNumber, ParserUtil.parseGroupNumber(groupNumberWithWhitespace));
    }

    @Test
    public void parseGroupType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroupType((String) null));
    }

    @Test
    public void parseGroupType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroupType(INVALID_GROUP_TYPE));
    }

    @Test
    public void parseGroupType_validValueWithoutWhitespace_returnsGroupType() throws Exception {
        GroupType expectedGroupType = new GroupType(VALID_GROUP_TYPE);
        assertEquals(expectedGroupType, ParserUtil.parseGroupType(VALID_GROUP_TYPE));
    }

    @Test
    public void parseGroupType_validValueWithWhitespace_returnsTrimmedGroupType() throws Exception {
        String groupTypeWithWhitespace = WHITESPACE + VALID_GROUP_TYPE + WHITESPACE;
        GroupType expectedGroupType = new GroupType(VALID_GROUP_TYPE);
        assertEquals(expectedGroupType, ParserUtil.parseGroupType(groupTypeWithWhitespace));
    }
}
