package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDEE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENUE_2;
import static seedu.address.testutil.TypicalAddressBookObjects.PRESENTATION;
import static seedu.address.testutil.TypicalAddressBookObjects.STAFF_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class MeetingTest {

    @Test
    public void isConflictingMeeting() {
        // same object -> returns true
        assertTrue(PRESENTATION.hasConflictWith(PRESENTATION));

        // null -> returns false
        assertFalse(PRESENTATION.hasConflictWith(null));

        // same dateTime, all other attributes different -> returns true
        Meeting editedPresentation = new MeetingBuilder(PRESENTATION).withAttendee(VALID_ATTENDEE_2)
                .withTitle(VALID_TITLE_2).withVenue(VALID_VENUE_2).build();

        assertTrue(PRESENTATION.hasConflictWith(editedPresentation));

        // different dateTime, all other attributes same -> returns true
        editedPresentation = new MeetingBuilder(PRESENTATION).withDateTime(VALID_DATETIME_2).build();
        assertFalse(PRESENTATION.hasConflictWith(editedPresentation));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Meeting presentationCopy = new MeetingBuilder(PRESENTATION).build();
        assertTrue(PRESENTATION.equals(presentationCopy));

        // same object -> returns true
        assertTrue(PRESENTATION.equals(PRESENTATION));

        // null -> returns false
        assertFalse(PRESENTATION.equals(null));

        // different type -> returns false
        assertFalse(PRESENTATION.equals(1));

        // different meeting -> returns false
        assertFalse(PRESENTATION.equals(STAFF_MEETING));

        // different dateTime -> returns false
        Meeting editedPresentation = new MeetingBuilder(PRESENTATION)
                .withDateTime(VALID_DATETIME_2).build();
        assertFalse(PRESENTATION.equals(editedPresentation));

        // different title -> returns false
        editedPresentation = new MeetingBuilder(PRESENTATION)
                .withTitle(VALID_TITLE_2).build();
        assertFalse(PRESENTATION.equals(editedPresentation));

        // different venue -> returns false
        editedPresentation = new MeetingBuilder(PRESENTATION)
                .withVenue(VALID_VENUE_2).build();
        assertFalse(PRESENTATION.equals(editedPresentation));

        // different attendee -> returns false
        editedPresentation = new MeetingBuilder(PRESENTATION)
                .withAttendee(VALID_ATTENDEE_2).build();
        assertFalse(PRESENTATION.equals(editedPresentation));
    }
}
