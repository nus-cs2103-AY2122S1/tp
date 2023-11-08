package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        // below 1 character
        assertThrows(IllegalArgumentException.class, () -> new Location(""));
        // more than 50 characters
        assertThrows(IllegalArgumentException.class, () -> new Location("1111111111111"
                + "11111111111111111111111111111111111111"));
        // non-alphanumeric characters
        assertThrows(IllegalArgumentException.class, () -> new Location("@#$"));
        assertThrows(IllegalArgumentException.class, () -> new Location("U_TOWN"));
    }

    @Test
    public void isValidLocation() {
        // null location
        assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        // valid location
        assertTrue(Location.isValidLocation("1")); // 1 character(lower boundary)
        assertTrue(Location.isValidLocation("1111111111111"
                + "1111111111111111111111111111111111111")); // 50 characters(upper boundary)
        assertTrue(Location.isValidLocation("25"));

        // invalid location
        assertFalse(Location.isValidLocation("")); // empty string
        assertFalse(Location.isValidLocation("")); // // below 1 character
        assertFalse(Location.isValidLocation("1111111111111"
                + "11111111111111111111111111111111111111")); // more than 50 characters
    }

    @Test
    public void equals() {
        Location location = new Location("Location");
        Location locationCopy = new Location("Location");

        assertTrue(location.equals(locationCopy));
        assertTrue(location.equals(location));

        assertFalse(location.equals(null));
        Location differentLocation = new Location("Somewhere");
        assertFalse(location.equals(differentLocation));

    }
}
