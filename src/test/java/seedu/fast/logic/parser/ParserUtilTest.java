package seedu.fast.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.fast.testutil.Assert.assertThrows;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.fast.logic.parser.exceptions.HelpParseException;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.Address;
import seedu.fast.model.person.Email;
import seedu.fast.model.person.Name;
import seedu.fast.model.person.Phone;
import seedu.fast.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_HELP_ARG_1 = "add";
    private static final String INVALID_HELP_ARG_2 = "archive";
    private static final String INVALID_HELP_COMMAND_1 = "help archive";
    private static final String INVALID_HELP_COMMAND_2 = "help random command";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_HELP_ARG_1 = "Add";
    private static final String VALID_HELP_ARG_2 = "";
    private static final String VALID_HELP_COMMAND_1 = "help help";
    private static final String VALID_HELP_COMMAND_2 = "help priority tag";
    private static final String VALID_HELP_COMMAND_OUTPUT_1 = "Help";
    private static final String VALID_HELP_COMMAND_OUTPUT_2 = "Priority Tag";
    private static final String VALID_DATE_INPUT = "2021-10-10";
    private static final String FORMATTED_DATE = "10 Oct 2021";
    private static final String VALID_TIME_INPUT = "20:00";
    private static final String FORMATTED_TIME = "2000";
    private static final String VALID_VENUE_INPUT = "Clementi Mall";

    private static final String SAMPLE_STRING = "the quick brown fox jumped over the lazy dog";

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
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
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
    public void matchArgs_withSmallLetters_success() {
        String actual = ParserUtil.matchArgs(INVALID_HELP_ARG_1);
        String expected = "";

        assertEquals(expected, actual);
    }

    @Test
    public void matchArgs_withBigLetters_success() {
        String actual = ParserUtil.matchArgs(VALID_HELP_ARG_1);
        String expected = "Add";

        assertEquals(expected, actual);
    }

    @Test
    public void matchArgs_noInput_success() {
        String actual = ParserUtil.matchArgs(VALID_HELP_ARG_2);
        String expected = "";

        assertEquals(expected, actual);
    }

    @Test
    public void matchArgs_invalidInput_success() {
        String actual = ParserUtil.matchArgs(INVALID_HELP_ARG_2);
        String expected = "";

        assertEquals(expected, actual);
    }

    @Test
    public void capitaliseFirstLetters_success() {
        String actual = ParserUtil.capitaliseFirstLetters(SAMPLE_STRING);
        String expected = "The Quick Brown Fox Jumped Over The Lazy Dog";

        assertEquals(expected, actual);
    }

    @Test
    public void parseHelp_oneWordValidInput_success() throws HelpParseException {
        String actual = ParserUtil.parseHelp(VALID_HELP_COMMAND_1);

        assertEquals(VALID_HELP_COMMAND_OUTPUT_1, actual);
    }

    @Test
    public void parseHelp_multipleWordsValidInput_success() throws HelpParseException {
        String actual = ParserUtil.parseHelp(VALID_HELP_COMMAND_2);

        assertEquals(VALID_HELP_COMMAND_OUTPUT_2, actual);
    }

    @Test
    public void parseHelp_invalidInput1_success() {
        assertThrows(HelpParseException.class, () -> ParserUtil.parseHelp(INVALID_HELP_COMMAND_1));
    }

    @Test
    public void parseHelp_invalidInput2_success() {
        assertThrows(HelpParseException.class, () -> ParserUtil.parseHelp(INVALID_HELP_COMMAND_2));
    }

    @Test
    public void parse_validDateFormat_success() throws ParseException {
        assertEquals(FORMATTED_DATE, ParserUtil.parseDateString(VALID_DATE_INPUT));
    }

    @Test
    public void parse_validTimeFormat_success() throws ParseException {
        assertEquals(FORMATTED_TIME, ParserUtil.parseTimeString(VALID_TIME_INPUT));
    }

    @Test
    public void parse_validVenueFormat_success() throws ParseException {
        assertEquals(VALID_VENUE_INPUT, ParserUtil.parseVenueString(VALID_VENUE_INPUT));
    }

    @Test
    public void parse_invalidDateFormat_failure() {
        // wrong order
        String invalidDateInput = "10-10-2021";
        assertThrows(ParseException.class, ()-> ParserUtil.parseDateString(invalidDateInput));

        // using text
        String invalidTextInput = "10-Oct-2021";
        assertThrows(ParseException.class, ()-> ParserUtil.parseDateString(invalidTextInput));

        // without dash
        String invalidNoDashInput = "10102021";
        assertThrows(ParseException.class, ()-> ParserUtil.parseDateString(invalidNoDashInput));
    }

    @Test
    public void parse_appointmentMonthOutOfBound_failure() {
        String invalidMonthInput = "2021-20-01";
        assertThrows(ParseException.class, ()-> ParserUtil.parseDateString(invalidMonthInput));
    }

    @Test
    public void parse_appointmentDayOutOfBound_failure() {
        String invalidDayInput = "2021-10-45";
        assertThrows(ParseException.class, ()-> ParserUtil.parseDateString(invalidDayInput));
    }

    @Test
    public void parse_invalidTimeFormat_failure() {
        // using 12-hour string-style format (i.e 8pm)
        String invalidTimeInput = "8pm";
        assertThrows(ParseException.class, ()-> ParserUtil.parseTimeString(invalidTimeInput));

        // without semi-colon
        assertThrows(ParseException.class, ()-> ParserUtil.parseTimeString(FORMATTED_TIME));

        // specify hour only
        String invalidSingleTimeInput = "8";
        assertThrows(ParseException.class, ()-> ParserUtil.parseTimeString(invalidSingleTimeInput));
    }

    @Test
    public void parse_appointmentHourOutOfBound_failure() {
        String invalidHourInput = "26:00";
        assertThrows(ParseException.class, ()-> ParserUtil.parseTimeString(invalidHourInput));
    }

    @Test
    public void parse_appointmentMinuteOutOfBound_failure() {
        String invalidMinuteInput = "08:80";
        assertThrows(ParseException.class, ()-> ParserUtil.parseTimeString(invalidMinuteInput));
    }

    @Test
    public void parse_invalidVenueInput_failure() {
        String invalidVenueInput = "Test1Test2Test3Test4Test5Test6Test7";
        assertThrows(ParseException.class, ()-> ParserUtil.parseVenueString(invalidVenueInput));
    }
}
