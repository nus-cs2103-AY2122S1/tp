package safeforhall.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static safeforhall.logic.commands.sort.SortPersonCommand.ASCENDING;
import static safeforhall.testutil.Assert.assertThrows;
import static safeforhall.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.ResidentList;
import safeforhall.model.event.Venue;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ROOM = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_FACULTY = " ";
    private static final String INVALID_VACCSTATUS = " ";
    private static final String INVALID_DATE = "1.10.2021";
    private static final String INVALID_ROOM_FOR_FIND1 = "AA";
    private static final String INVALID_ROOM_FOR_FIND2 = "A12";
    private static final String INVALID_ROOM_FOR_FIND3 = "12";
    private static final String INVALID_RESIDENTS = "Alex Yeoh, C11";
    private static final String INVALID_EVENTNAME = "F@@tball";
    private static final String INVALID_EVENTDATE = "03";
    private static final String INVALID_EVENTTIME = "time";
    private static final String INVALID_VENUE = " ";
    private static final String INVALID_CAPACITY = "CAP5.0";
    private static final String INVALID_FILENAME = "file name";
    private static final String INVALID_FIELD = "z";
    private static final String INVALID_ORDER = "c";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ROOM = "A100";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_DATE = "21-10-2021";
    private static final String VALID_FACULTY = "SoC";
    private static final String VALID_VACCSTATUS = "T";
    private static final String VALID_ROOM_FOR_FIND1 = "A";
    private static final String VALID_ROOM_FOR_FIND2 = "A1";
    private static final String VALID_ROOM_FOR_FIND3 = "E200";
    private static final String VALID_RESIDENTS = "Alex Yeoh, Bernice Yu";
    private static final String VALID_EVENTNAME = "Training";
    private static final String VALID_EVENTDATE = "03-01-2021";
    private static final String VALID_EVENTTIME = "0600";
    private static final String VALID_VENUE = "NUS field";
    private static final String VALID_CAPACITY = "5";
    private static final String VALID_FILENAME = "filename";

    private static final String EMPTY_STRING = "";
    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, ()
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
    public void parseRoom_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRoom((String) null));
    }

    @Test
    public void parseRoom_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRoom(INVALID_ROOM));
    }

    @Test
    public void parseRoom_validValueWithoutWhitespace_returnsRoom() throws Exception {
        Room expectedRoom = new Room(VALID_ROOM);
        assertEquals(expectedRoom, ParserUtil.parseRoom(VALID_ROOM));
    }

    @Test
    public void parseRoom_validValueWithWhitespace_returnsTrimmedRoom() throws Exception {
        String roomWithWhitespace = WHITESPACE + VALID_ROOM + WHITESPACE;
        Room expectedRoom = new Room(VALID_ROOM);
        assertEquals(expectedRoom, ParserUtil.parseRoom(roomWithWhitespace));
    }

    @Test
    public void parseRoomForFind_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRoomForFind(INVALID_ROOM_FOR_FIND1));
    }

    @Test
    public void parseRoomForFind_invalidValue2_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRoomForFind(INVALID_ROOM_FOR_FIND2));
    }

    @Test
    public void parseRoomForFind_invalidValue3_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRoomForFind(INVALID_ROOM_FOR_FIND3));
    }

    @Test
    public void parseRoomForFind_validValue1_returnsTrimmedRoom() throws Exception {
        String roomWithWhitespace = WHITESPACE + VALID_ROOM_FOR_FIND1 + WHITESPACE;
        assertEquals(VALID_ROOM_FOR_FIND1, ParserUtil.parseRoomForFind(roomWithWhitespace));
    }

    @Test
    public void parseRoomForFind_validValue2_returnsTrimmedRoom() throws Exception {
        String roomWithWhitespace = WHITESPACE + VALID_ROOM_FOR_FIND2 + WHITESPACE;
        assertEquals(VALID_ROOM_FOR_FIND2, ParserUtil.parseRoomForFind(roomWithWhitespace));
    }

    @Test
    public void parseRoomForFind_validValue3_returnsTrimmedRoom() throws Exception {
        String roomWithWhitespace = WHITESPACE + VALID_ROOM_FOR_FIND3 + WHITESPACE;
        assertEquals(VALID_ROOM_FOR_FIND3, ParserUtil.parseRoomForFind(roomWithWhitespace));
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
    public void parseVaccStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseVaccStatus((String) null));
    }

    @Test
    public void parseVaccStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVaccStatus(INVALID_VACCSTATUS));
    }

    @Test
    public void parseVaccStatus_validValueWithoutWhitespace_returnsVaccStatus() throws Exception {
        VaccStatus expectedVaccStatus = new VaccStatus(VALID_VACCSTATUS);
        assertEquals(expectedVaccStatus, ParserUtil.parseVaccStatus(VALID_VACCSTATUS));
    }

    @Test
    public void parseVaccStatus_validValueWithWhitespace_returnsTrimmedVaccStatus() throws Exception {
        String vaccStatusWithWhitespace = WHITESPACE + VALID_VACCSTATUS + WHITESPACE;
        VaccStatus expectedVaccStatus = new VaccStatus(VALID_VACCSTATUS);
        assertEquals(expectedVaccStatus, ParserUtil.parseVaccStatus(vaccStatusWithWhitespace));
    }

    @Test
    public void parseFaculty_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFaculty((String) null));
    }

    @Test
    public void parseFaculty_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFaculty(INVALID_FACULTY));
    }

    @Test
    public void parseFaculty_validValueWithoutWhitespace_returnsFaculty() throws Exception {
        Faculty expectedFaculty = new Faculty(VALID_FACULTY);
        assertEquals(expectedFaculty, ParserUtil.parseFaculty(VALID_FACULTY));
    }

    @Test
    public void parseFaculty_validValueWithWhitespace_returnsTrimmedFaculty() throws Exception {
        String facultyWithWhitespace = WHITESPACE + VALID_FACULTY + WHITESPACE;
        Faculty expectedFaculty = new Faculty(VALID_FACULTY);
        assertEquals(expectedFaculty, ParserUtil.parseFaculty(facultyWithWhitespace));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsLastDate() throws Exception {
        LastDate expectedDate = new LastDate(VALID_DATE);
        Assertions.assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedLastDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        LastDate expectedDate = new LastDate(VALID_DATE);
        Assertions.assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhitespace));
    }

    @Test
    public void parseResidents_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseResidents((String) null));
    }

    @Test
    public void parseResidents_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseResidents(INVALID_RESIDENTS));
    }

    @Test
    public void parseResidents_validValueWithoutWhitespace_returnsLastDate() throws Exception {
        ResidentList expectedList = new ResidentList(VALID_RESIDENTS);
        Assertions.assertEquals(expectedList, ParserUtil.parseResidents(VALID_RESIDENTS));
    }

    @Test
    public void parseResidents_validValueWithWhitespace_returnsTrimmedLastDate() throws Exception {
        String listWithWhitespace = WHITESPACE + VALID_RESIDENTS + WHITESPACE;
        ResidentList expectedList = new ResidentList(VALID_RESIDENTS);
        Assertions.assertEquals(expectedList, ParserUtil.parseResidents(listWithWhitespace));
    }

    @Test
    public void parseEventName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventName((String) null));
    }

    @Test
    public void parseEventName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventName(INVALID_EVENTNAME));
    }

    @Test
    public void parseEventDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventDate((String) null));
    }

    @Test
    public void parseEventDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate(INVALID_EVENTDATE));
    }

    @Test
    public void parseEventVenue_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseVenue((String) null));
    }

    @Test
    public void parseEventDate_invalidVenue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVenue(INVALID_VENUE));
    }

    @Test
    public void parseCapacity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCapacity((String) null));
    }

    @Test
    public void parseCapacity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCapacity(INVALID_CAPACITY));
    }

    @Test
    public void parseEventName_validValueWithoutWhitespace_returnsEventName() throws Exception {
        EventName expectedEventName = new EventName(VALID_EVENTNAME);
        assertEquals(expectedEventName, ParserUtil.parseEventName(VALID_EVENTNAME));
    }

    @Test
    public void parseEventName_validValueWithWhitespace_returnsTrimmedEventName() throws Exception {
        String eventNameWithWhitespace = WHITESPACE + VALID_EVENTNAME + WHITESPACE;
        EventName expectedEventName = new EventName(VALID_EVENTNAME);
        assertEquals(expectedEventName, ParserUtil.parseEventName(eventNameWithWhitespace));
    }

    @Test
    public void parseEventDate_validValueWithoutWhitespace_returnsEventDate() throws Exception {
        EventDate expectedEventDate = new EventDate(VALID_EVENTDATE);
        assertEquals(expectedEventDate, ParserUtil.parseEventDate(VALID_EVENTDATE));
    }

    @Test
    public void parseEventDate_validValueWithWhitespace_returnsTrimmedEventDate() throws Exception {
        String eventDateWithWhitespace = WHITESPACE + VALID_EVENTDATE + WHITESPACE;
        EventDate expectedEventDate = new EventDate(VALID_EVENTDATE);
        assertEquals(expectedEventDate, ParserUtil.parseEventDate(eventDateWithWhitespace));
    }

    @Test
    public void parseEventTime_validValueWithoutWhitespace_returnsEventTime() throws Exception {
        EventTime expectedEventTime = new EventTime(VALID_EVENTTIME);
        assertEquals(expectedEventTime, ParserUtil.parseEventTime(VALID_EVENTTIME));
    }

    @Test
    public void parseEventTime_validValueWithWhitespace_returnsTrimmedEventTime() throws Exception {
        String eventTimeWithWhitespace = WHITESPACE + VALID_EVENTTIME + WHITESPACE;
        EventTime expectedEventTime = new EventTime(VALID_EVENTTIME);
        assertEquals(expectedEventTime, ParserUtil.parseEventTime(eventTimeWithWhitespace));
    }

    @Test
    public void parseVenue_validValueWithoutWhitespace_returnsVenue() throws Exception {
        Venue expectedVenue = new Venue(VALID_VENUE);
        assertEquals(expectedVenue, ParserUtil.parseVenue(VALID_VENUE));
    }

    @Test
    public void parseVenue_validValueWithWhitespace_returnsTrimmedVenue() throws Exception {
        String venueWithWhitespace = WHITESPACE + VALID_VENUE + WHITESPACE;
        Venue expectedVenue = new Venue(VALID_VENUE);
        assertEquals(expectedVenue, ParserUtil.parseVenue(venueWithWhitespace));
    }

    @Test
    public void parseCapacity_validValueWithoutWhitespace_returnsCapacity() throws Exception {
        Capacity expectedCapacity = new Capacity(VALID_CAPACITY);
        assertEquals(expectedCapacity, ParserUtil.parseCapacity(VALID_CAPACITY));
    }

    @Test
    public void parseCapacity_validValueWithWhitespace_returnsTrimmedCapacity() throws Exception {
        String capacityWithWhitespace = WHITESPACE + VALID_CAPACITY + WHITESPACE;
        Capacity expectedCapacity = new Capacity(VALID_CAPACITY);
        assertEquals(expectedCapacity, ParserUtil.parseCapacity(capacityWithWhitespace));
    }

    @Test
    public void parseExportFileName_emptyValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseExportFileName(EMPTY_STRING));
    }

    @Test
    public void parseExportFileName_null_throwsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExportFileName((String) null));
    }

    @Test
    public void parseExportFileName_validValue_returnsString() throws Exception {
        String expectedFileName = VALID_FILENAME;
        assertEquals(expectedFileName, ParserUtil.parseExportFileName(VALID_FILENAME));
    }

    @Test
    public void parseExportFileName_validValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseExportFileName(INVALID_FILENAME));
    }

    @Test
    public void parseImportFileName_emptyValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseImportFileName(EMPTY_STRING));
    }

    @Test
    public void parseImportFileName_null_throwsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseImportFileName((String) null));
    }

    @Test
    public void parseImportFileName_validValue_returnsString() throws Exception {
        String expectedFileName = VALID_FILENAME;
        assertEquals(expectedFileName, ParserUtil.parseImportFileName(VALID_FILENAME));
    }

    @Test
    public void parseImportFileName_validValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseImportFileName(INVALID_FILENAME));
    }

    @Test
    public void parsePersonField_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePersonField((String) null));
    }

    @Test
    public void parsePersonField_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePersonField(EMPTY_STRING));
    }

    @Test
    public void parsePersonField_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePersonField(INVALID_FIELD));
    }

    @Test
    public void parsePersonField_validValue_returnsString() throws Exception {
        String expectedField = Name.FIELD;
        assertEquals(expectedField, ParserUtil.parsePersonField(Name.FIELD));
    }

    @Test
    public void parsePersonField_validValueWithWhitespace_returnsTrimmedField() throws Exception {
        String fieldWithWhitespace = WHITESPACE + Name.FIELD + WHITESPACE;
        String expectedField = Name.FIELD;
        assertEquals(expectedField, ParserUtil.parsePersonField(fieldWithWhitespace));
    }

    @Test
    public void parseEventField_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventField((String) null));
    }

    @Test
    public void parseEventField_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventField(EMPTY_STRING));
    }

    @Test
    public void parseEventField_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventField(INVALID_FIELD));
    }

    @Test
    public void parseEventField_validValue_returnsString() throws Exception {
        String expectedField = EventName.FIELD;
        assertEquals(expectedField, ParserUtil.parseEventField(Name.FIELD));
    }

    @Test
    public void parseEventField_validValueWithWhitespace_returnsTrimmedField() throws Exception {
        String fieldWithWhitespace = WHITESPACE + EventName.FIELD + WHITESPACE;
        String expectedField = EventName.FIELD;
        assertEquals(expectedField, ParserUtil.parseEventField(fieldWithWhitespace));
    }

    @Test
    public void parseOrder_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrder((String) null));
    }

    @Test
    public void parseOrder_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrder(EMPTY_STRING));
    }

    @Test
    public void parseOrder_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrder(INVALID_ORDER));
    }

    @Test
    public void parseOrder_validValue_returnsString() throws Exception {
        String expectedOrder = ASCENDING;
        assertEquals(expectedOrder, ParserUtil.parseOrder(ASCENDING));
    }

    @Test
    public void parseOrder_validValueWithWhitespace_returnsTrimmedField() throws Exception {
        String orderWithWhitespace = WHITESPACE + ASCENDING + WHITESPACE;
        String expectedOrder = ASCENDING;
        assertEquals(expectedOrder, ParserUtil.parseOrder(orderWithWhitespace));
    }
}
