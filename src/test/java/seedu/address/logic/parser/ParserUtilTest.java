package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PARTICIPANT;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.participant.Address;
import seedu.address.model.participant.BirthDate;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.Note;
import seedu.address.model.participant.ParticipantId;
import seedu.address.model.participant.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String VALID_NOTE_1 = "VERY_HIGH:Vegetarian";
    private static final String VALID_NOTE_2 = "MEDIUM:Fever";
    private static final String VALID_NOTE_3 = "LOW:Male";

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
        assertEquals(INDEX_FIRST_PARTICIPANT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PARTICIPANT, ParserUtil.parseIndex("  1  "));
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
    public void parseBirthDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBirthDate(null));
    }

    @Test
    public void parseBirthDate_notSpecifiedValidDate_returnsBirthDate() throws Exception {
        BirthDate actualBirthDate = ParserUtil.parseBirthDate("NA");
        BirthDate expectedBirthDate = BirthDate.notSpecified();
        assertEquals(expectedBirthDate, actualBirthDate);
    }

    @Test
    public void parseBirthDate_invalidBirthDate_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthDate("2021-13-10"));
    }

    @Test
    public void parseBirthDate_specifiedValidDate_returnsBirthDate() throws Exception {
        BirthDate expectedBirthDate = BirthDate.of(2020, 11, 30);
        BirthDate actualBirthDate = ParserUtil.parseBirthDate("2020-11-30");
        assertEquals(expectedBirthDate, actualBirthDate);
    }

    @Test
    public void parseImportance_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseImportance(null));
    }

    @Test
    public void parseImportance_validImportance_returnsImportance() throws Exception {
        // very high
        Note.Importance veryHigh = ParserUtil.parseImportance("VERY_HIGH");
        assertEquals(veryHigh, Note.Importance.VERY_HIGH);

        // high
        Note.Importance high = ParserUtil.parseImportance("HIGH");
        assertEquals(high, Note.Importance.HIGH);

        // medium
        Note.Importance medium = ParserUtil.parseImportance("MEDIUM");
        assertEquals(medium, Note.Importance.MEDIUM);

        // low
        Note.Importance low = ParserUtil.parseImportance("LOW");
        assertEquals(low, Note.Importance.LOW);

        // very low
        Note.Importance veryLow = ParserUtil.parseImportance("VERY_LOW");
        assertEquals(veryLow, Note.Importance.VERY_LOW);
    }

    @Test
    public void parseImportance_invalidImportance_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseImportance("Priority"));
        assertThrows(ParseException.class, () -> ParserUtil.parseImportance("1234"));
        assertThrows(ParseException.class, () -> ParserUtil.parseImportance("!@#!$!@"));
        assertThrows(ParseException.class, () -> ParserUtil.parseImportance(".-12-3pdax12e"));
    }

    @Test
    public void parseNote_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNote(null));
    }

    @Test
    public void parseNote_validNote_returnNote() throws Exception {
        Note expectedNote = new Note("Vegetarian", Note.Importance.VERY_HIGH);
        Note actualNote = ParserUtil.parseNote(VALID_NOTE_1);
        assertEquals(expectedNote, actualNote);
    }

    @Test
    public void parseNote_invalidNote_throwsParseException() {
        // missing content and importance
        assertThrows(ParseException.class, () -> ParserUtil.parseNote(":"));

        // missing content
        assertThrows(ParseException.class, () -> ParserUtil.parseNote("VERY_HIGH:"));

        // missing semicolon
        assertThrows(ParseException.class, () -> ParserUtil.parseNote("VERY_HIGH"));

        // invalid Importance
        assertThrows(ParseException.class, () -> ParserUtil.parseNote("VERY_HIGHER:Vegetarian"));
    }

    @Test
    public void parseNotes_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNotes(null));
    }

    @Test
    public void parseNotes_validNotes_returnSetOfNotes() throws Exception {
        Collection<String> notes = Arrays.asList(VALID_NOTE_1, VALID_NOTE_2, VALID_NOTE_3);
        Set<Note> actualSetOfNotes = ParserUtil.parseNotes(notes);
        Set<Note> expectedSetOfNotes = new HashSet<>();
        expectedSetOfNotes.add(ParserUtil.parseNote(VALID_NOTE_1));
        expectedSetOfNotes.add(ParserUtil.parseNote(VALID_NOTE_2));
        expectedSetOfNotes.add(ParserUtil.parseNote(VALID_NOTE_3));
        assertEquals(expectedSetOfNotes, actualSetOfNotes);
    }

    @Test
    public void parseEventName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventName(null));
    }

    @Test
    public void parseEventName_invalidEventName_throwsParseException() {
        // empty name
        assertThrows(ParseException.class, () -> ParserUtil.parseEventName(""));

        // invalid characters
        assertThrows(ParseException.class, () -> ParserUtil.parseEventName("Race @ SG"));
    }

    @Test
    public void parseEventName_validEventName_returnsEventName() throws Exception {
        EventName expectedEventName = new EventName("Race for sanity");
        EventName actualEventName = ParserUtil.parseEventName("Race for sanity");
        assertEquals(expectedEventName, actualEventName);
    }

    @Test
    public void parseEventDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventDate(null));
    }

    @Test
    public void parseEventDate_invalidDate_throwsParseException() {
        // invalid year
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate("-2021-10-11"));

        // invalid month
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate("2021-130-11"));

        // invalid day
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate("2021-10-320"));

        // invalid characters
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate("20th May 2021"));

        // empty date
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate(""));
    }

    @Test
    public void parseEventDate_validDate_returnsEventDate() throws Exception {
        EventDate expectedEventDate = new EventDate("2021-11-30");
        EventDate actualEventDate = ParserUtil.parseEventDate("2021-11-30");
        assertEquals(expectedEventDate, actualEventDate);
    }

    @Test
    public void parseEventTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventTime(null));
    }

    @Test
    public void parseEventTime_invalidTime_throwsParseException() {
        // invalid hour
        assertThrows(ParseException.class, () -> ParserUtil.parseEventTime("2500"));

        // invalid minutes
        assertThrows(ParseException.class, () -> ParserUtil.parseEventTime("2060"));

        // invalid hour and minutes
        assertThrows(ParseException.class, () -> ParserUtil.parseEventTime("2560"));

        // invalid length
        assertThrows(ParseException.class, () -> ParserUtil.parseEventTime("500"));

        // invalid characters
        assertThrows(ParseException.class, () -> ParserUtil.parseEventTime("abcd"));
    }

    @Test
    public void parseEventTime_validTime_returnEventTime() throws Exception {
        // no time
        EventTime actualNoTime = new EventTime();
        EventTime expectedNoTime = ParserUtil.parseEventTime("");
        assertEquals(expectedNoTime, actualNoTime);

        // hasTime
        EventTime actualSomeTime = new EventTime("2359");
        EventTime expectedSomeTime = ParserUtil.parseEventTime("2359");
        assertEquals(expectedSomeTime, actualSomeTime);
    }

    @Test
    public void parseParticipantId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseParticipantId(null));
    }

    @Test
    public void parseParticipantId_invalidId_throwsParseException() {
        // invalid id numbering
        assertThrows(ParseException.class, () -> ParserUtil.parseParticipantId("aleyeo0"));

        // empty id numbering
        assertThrows(ParseException.class, () -> ParserUtil.parseParticipantId("aleyeo"));

        // invalid alphabets length
        assertThrows(ParseException.class, () -> ParserUtil.parseParticipantId("alexyeo0"));

        // empty alphabet
        assertThrows(ParseException.class, () -> ParserUtil.parseParticipantId("0"));

        // empty id
        assertThrows(ParseException.class, () -> ParserUtil.parseParticipantId(""));
    }

    @Test
    public void parseParticipantId_validId_returnsParticipantId() throws Exception {
        ParticipantId expectedId = new ParticipantId("aleyeo1");
        ParticipantId actualId = ParserUtil.parseParticipantId("aleyeo1");
        assertEquals(expectedId, actualId);
    }

}
