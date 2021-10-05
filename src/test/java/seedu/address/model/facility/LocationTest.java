package seedu.address.model.facility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class LocationTest {

    @Test
    public void constructor_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new Location(null));
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
