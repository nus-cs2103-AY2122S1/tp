package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class AvailabilityTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Availability(null));
    }

    @Test
    public void isValidAvailability() {
        // null availability
        assertThrows(NullPointerException.class, () -> Availability.isValidAvailability(null));

        // invalid availabilities
        assertFalse(Availability.isValidAvailability(Arrays.asList("one", "two"))); // not integer
        assertFalse(Availability.isValidAvailability(Arrays.asList("0", "8"))); // out of range

        // valid availabilities
        assertTrue(Availability.isValidAvailability(Arrays.asList("1", "2")));
        assertTrue(Availability.isValidAvailability(Arrays.asList("")));
        assertTrue(Availability.isValidAvailability(Arrays.asList("1", "2", "3", "4", "5", "6", "7")));
    }
}
