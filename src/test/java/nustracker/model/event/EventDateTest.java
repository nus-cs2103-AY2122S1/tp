package nustracker.model.event;

import static nustracker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventTime(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new EventDate(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null event dates
        assertThrows(NullPointerException.class, () -> EventDate.isValidDate(null));

        // invalid event dates
        Assertions.assertFalse(EventDate.isValidDate("")); // empty string
        Assertions.assertFalse(EventDate.isValidDate(" ")); // spaces only
        Assertions.assertFalse(EventDate.isValidDate("09 10 2021")); // spaces instead of dash
        Assertions.assertFalse(EventDate.isValidDate("09102021")); // no dashes
        Assertions.assertFalse(EventDate.isValidDate("09-ab-2021")); // alphabets within digits
        Assertions.assertFalse(EventDate.isValidDate("09-13-2021")); // invalid month
        Assertions.assertFalse(EventDate.isValidDate("09-00-2021")); // invalid month
        Assertions.assertFalse(EventDate.isValidDate("32-10-2021")); // invalid day
        Assertions.assertFalse(EventDate.isValidDate("00-10-2021")); // invalid day

        // month does not contain the day
        Assertions.assertFalse(EventDate.isValidDate("29-02-2021"));
        Assertions.assertFalse(EventDate.isValidDate("31-04-2021"));
        Assertions.assertFalse(EventDate.isValidDate("31-06-2021"));

        // valid event dates
        Assertions.assertTrue(EventDate.isValidDate("09-10-2021")); // current date
        Assertions.assertTrue(EventDate.isValidDate("31-12-3000")); // late date
        Assertions.assertTrue(EventDate.isValidDate("01-01-1900")); // early date
        Assertions.assertTrue(EventDate.isValidDate("29-02-2024")); // leap year
    }

}
