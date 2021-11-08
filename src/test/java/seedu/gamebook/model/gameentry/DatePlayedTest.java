package seedu.gamebook.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.gamebook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DatePlayedTest {

    @Test
    public void constructor_invalidDatetime_throwsIllegalArgumentException() {
        // Valid datetime
        assertDoesNotThrow(() -> new DatePlayed("2012-12-12 12:12"));

        // Invalid date
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-12-123 12:12"));

        // Invalid date
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-12-32 12:12"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-32-12 12:12"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-0-12 12:12"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-13-12 12:12"));

        // Invalid hour
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-12-12 123:12"));

        // Invalid hour
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-12-12 25:12"));

        // Invalid minute
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-12-12 12:123"));

        // Invalid minute
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-12-12 25:60"));
    }

    @Test
    public void constructor_invalidDateFormat_throwsIllegalArgumentException() {
        // Valid datetime
        assertDoesNotThrow(() -> new DatePlayed("2012-12-12 12:12"));

        // Valid date
        assertDoesNotThrow(() -> new DatePlayed("2012-12-12 12:12"));

        // Invalid datetime format
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012/12/12 12:12"));

        // Invalid date format
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012/12/12"));

    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        // Valid date
        assertDoesNotThrow(() -> new DatePlayed("2012-12-12"));

        // Invalid date
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-12-123"));

        // Invalid date
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-12-32"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-123-12"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-0-12"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("2012-13-12"));
    }

    @Test
    public void equals_condition_equalIfSameMinute() {
        // Exact same time
        DatePlayed date0 = new DatePlayed("2021-05-10 11:09");
        DatePlayed date1 = new DatePlayed("2021-05-10 11:09");
        assertEquals(date0, date1);

        // Different minute
        date0 = new DatePlayed("2021-05-10 11:09");
        date1 = new DatePlayed("2021-05-10 11:06");
        assertNotEquals(date0, date1);
    }

    @Test
    public void equals_condition_equalIfSameDate() {
        // Same date
        DatePlayed date0 = new DatePlayed("2021-05-10");
        DatePlayed date1 = new DatePlayed("2021-05-10");
        assertEquals(date0, date1);

        // Different date
        date0 = new DatePlayed("2021-05-02");
        date1 = new DatePlayed("2021-05-10");
        assertNotEquals(date0, date1);

        // Different month
        date0 = new DatePlayed("2021-05-10");
        date1 = new DatePlayed("2021-06-10");
        assertNotEquals(date0, date1);

        // Different year
        date0 = new DatePlayed("2020-05-10");
        date1 = new DatePlayed("2021-05-10");
        assertNotEquals(date0, date1);
    }

    @Test
    public void equal_condition_notEqualIfDifferentFormat() {
        DatePlayed date0;
        DatePlayed date1;

        // Same date, but one of them has time indicated
        date0 = new DatePlayed("2021-10-02 12:34");
        date1 = new DatePlayed("2021-10-02");
        assertNotEquals(date0, date1);
    }

    @Test
    public void toStringTest() {
        DatePlayed date0;

        // toString does not print time if time is not specified
        date0 = new DatePlayed("2021-10-05");
        assertEquals(date0.toString(), "2021-10-05");

        // toString prints time if time is specified
        date0 = new DatePlayed("2021-10-05 11:09");
        assertEquals(date0.toString(), "2021-10-05 11:09");
    }

}
