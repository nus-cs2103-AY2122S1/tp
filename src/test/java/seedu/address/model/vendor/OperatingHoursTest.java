package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class OperatingHoursTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OperatingHours(null, null, null, null));
    }

    @Test
    public void constructor_invalidOperatingHours_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, (
        ) -> new OperatingHours(LocalTime.of(20, 0), LocalTime.of(10, 0), new ArrayList<>(), "1 2000-1000"));
    }

    @Test
    public void isValidOperatingHours() {
        // null operatingHours
        assertThrows(NullPointerException.class, () -> OperatingHours.isValidOperatingHours(null));

        // blank operatingHours
        assertFalse(OperatingHours.isValidOperatingHours("")); // empty string
        assertFalse(OperatingHours.isValidOperatingHours(" ")); // spaces only

        // missing parts
        assertFalse(OperatingHours.isValidOperatingHours("1234 ")); // missing timings
        assertFalse(OperatingHours.isValidOperatingHours("123451000-1200")); // missing space
        assertFalse(OperatingHours.isValidOperatingHours("1234 1000-")); // missing start time
        assertFalse(OperatingHours.isValidOperatingHours("1234 -1200")); // missing end time
        assertFalse(OperatingHours.isValidOperatingHours("1234 1000 1200")); // missing '-'

        // invalid parts
        assertFalse(OperatingHours.isValidOperatingHours("1230 1000-1200")); // invalid days of week
        assertFalse(OperatingHours.isValidOperatingHours("1234  1000-1200")); // more than one spacing
        assertFalse(OperatingHours.isValidOperatingHours("1234 800-200")); // does not follow 4 number format
        assertFalse(OperatingHours.isValidOperatingHours("1234 1799-1290")); // does not follow 24 hour format

        // valid operatingHours
        assertTrue(OperatingHours.isValidOperatingHours("123 1000-1200"));
        assertTrue(OperatingHours.isValidOperatingHours("12311111111 1000-1200")); // duplicates numbers accepted

    }

}
