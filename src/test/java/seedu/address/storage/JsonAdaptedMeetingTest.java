package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBookObjects.MEET_PARENTS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.DescriptionType;

public class JsonAdaptedMeetingTest {
    private static final String INVALID_DATETIME = "19-08-2022 02:02";
    private static final String INVALID_ATTENDEE = "-";
    private static final String INVALID_TITLE = " ";
    private static final String INVALID_VENUE = "";

    private static final String VALID_DATETIME = MEET_PARENTS.getDateTime().toString();
    private static final String VALID_ATTENDEE = MEET_PARENTS.getAttendee().toString();
    private static final String VALID_TITLE = MEET_PARENTS.getTitle().toString();
    private static final String VALID_VENUE = MEET_PARENTS.getVenue().toString();

    @Test
    public void toModelType_validMeetingDetails_returnsMeeting() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(MEET_PARENTS);
        assertEquals(MEET_PARENTS, meeting.toModelType());
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(
                INVALID_DATETIME, VALID_TITLE, VALID_VENUE, VALID_ATTENDEE);

        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(
                null, VALID_TITLE, VALID_VENUE, VALID_ATTENDEE);

        String expectedMessage = String.format(JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT,
                DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidAttendee_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(
                VALID_DATETIME, VALID_TITLE, VALID_VENUE, INVALID_ATTENDEE);

        String expectedMessage = Attendee.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullAttendee_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(
                VALID_DATETIME, VALID_TITLE, VALID_VENUE, null);

        String expectedMessage = String.format(JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT,
                Attendee.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(
                VALID_DATETIME, INVALID_TITLE, VALID_VENUE, VALID_ATTENDEE);

        String expectedMessage = Description.getMessageConstraints(DescriptionType.TITLE);
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(
                VALID_DATETIME, null, VALID_VENUE, VALID_ATTENDEE);

        String expectedMessage = String.format(JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT,
                "Title");
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidVenue_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(
                VALID_DATETIME, VALID_TITLE, INVALID_VENUE, VALID_ATTENDEE);

        String expectedMessage = Description.getMessageConstraints(DescriptionType.VENUE);
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullVenue_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(
                VALID_DATETIME, VALID_TITLE, null, VALID_ATTENDEE);

        String expectedMessage = String.format(JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT,
                "Venue");
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }
}
