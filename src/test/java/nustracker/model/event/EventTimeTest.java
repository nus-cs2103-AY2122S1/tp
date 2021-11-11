package nustracker.model.event;

import static nustracker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventDate(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new EventTime(invalidTime));
    }

    @Test
    public void isValidTime() {

        assertThrows(NullPointerException.class, () -> EventTime.isValidTime(null));

        // invalid phone numbers
        Assertions.assertFalse(EventTime.isValidTime("")); // empty string
        Assertions.assertFalse(EventTime.isValidTime(" ")); // spaces only
        Assertions.assertFalse(EventTime.isValidTime("12:00 pm")); // 12 hr time
        Assertions.assertFalse(EventTime.isValidTime("15000")); // too many numbers
        Assertions.assertFalse(EventTime.isValidTime("2401")); // too large
        Assertions.assertFalse(EventTime.isValidTime("-1300")); // negative
        Assertions.assertFalse(EventTime.isValidTime("19-00"));

        // valid phone numbers
        Assertions.assertTrue(EventTime.isValidTime("1500"));
        Assertions.assertTrue(EventTime.isValidTime("2359"));
        Assertions.assertTrue(EventTime.isValidTime("0000"));
        Assertions.assertTrue(EventTime.isValidTime("2400"));
    }

}
