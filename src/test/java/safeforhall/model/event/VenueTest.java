package safeforhall.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VenueTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Venue(null));
    }

    @Test
    public void constructor_invalidVenue_throwsIllegalArgumentException() {
        String invalidVenue = "";
        assertThrows(IllegalArgumentException.class, () -> new Venue(invalidVenue));
    }

    @Test
    public void isValidVenue() {
        // null venue
        assertThrows(NullPointerException.class, () -> Venue.isValidVenue(null));

        // invalid venue
        assertFalse(Venue.isValidVenue("")); // empty string
        assertFalse(Venue.isValidVenue(" ")); // spaces only
        assertFalse(Venue.isValidVenue("^")); // only non-alphanumeric characters

        // valid venue
        assertTrue(Venue.isValidVenue("Football training"));
        assertTrue(Venue.isValidVenue("Frisbee training"));
    }

    @Test
    public void checkCompareTo() {
        Venue v1 = new Venue("A");
        Venue v2 = new Venue("B");
        Venue v3 = new Venue("a");

        assertEquals(v1.compareTo(v2), -1);
        assertEquals(v2.compareTo(v1), 1);
        assertEquals(v3.compareTo(v1), 0);
    }
}
