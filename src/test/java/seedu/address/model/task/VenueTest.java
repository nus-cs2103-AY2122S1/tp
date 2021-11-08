package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VenueTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Venue(null));
    }

    @Test
    public void constructor_invalidVenue_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Venue(invalidName));
    }

    @Test
    public void isValidVenue() {
        // null task venue
        assertThrows(NullPointerException.class, () -> Venue.isValidVenue(null));

        // invalid task venue
        assertFalse(Venue.isValidVenue("")); // empty string
        assertFalse(Venue.isValidVenue(" ")); // spaces only
        assertFalse(Venue.isValidVenue(" Zoom")); // first character is white space

        // valid task venue
        assertTrue(Venue.isValidVenue("Jurong Library")); // alphabets only
        assertTrue(Venue.isValidVenue("12345")); // numbers only
        assertTrue(Venue.isValidVenue("capitol 2nd floor")); // alphanumeric characters
        assertTrue(Venue.isValidVenue("CLEMENTI MALL")); // with capital letters
        assertTrue(Venue.isValidVenue("Marina Bay Sands Casino and Resort")); // long venues
        assertTrue(Venue.isValidVenue("Task@Idaho")); // special characters included
    }
}
