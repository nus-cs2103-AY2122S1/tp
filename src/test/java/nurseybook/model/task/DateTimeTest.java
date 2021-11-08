package nurseybook.model.task;

import static nurseybook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DateTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime((String) null, (String) null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDate = "8 july";
        String invalidTime = "12.12am";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDate, invalidTime));
    }

    @Test
    public void isValidDateTime() {
        // null date, time
        assertThrows(NullPointerException.class, () -> DateTime.isValidDate(null));
        assertThrows(NullPointerException.class, () -> DateTime.isValidTime(null));

        // invalid dates
        assertFalse(DateTime.isValidDate("bamboo")); // nonsense
        assertFalse(DateTime.isValidDate("8 july")); // wrong format
        assertFalse(DateTime.isValidDate("2010-02-31")); // invalid value

        // invalid times
        assertFalse(DateTime.isValidTime("edward cullen"));
        assertFalse(DateTime.isValidTime("12.12am")); // wrong format
        assertFalse(DateTime.isValidTime("70:99")); // correct format, invalid value

        // valid dates
        assertTrue(DateTime.isValidDate("2021-12-11"));
        assertTrue(DateTime.isValidDate("2000-01-01")); //time in the past

        // valid time
        assertTrue(DateTime.isValidTime("23:00")); // 24 hours time
        assertTrue(DateTime.isValidTime("08:32:30")); // with seconds
    }

    @Test
    public void isAfter() {
        DateTime now = new DateTime("2021-10-31", "10:25");

        // before the DateTime to test against -> returns true
        DateTime beforeDate = new DateTime("2021-09-15", "13:00"); // different date
        DateTime beforeTime = new DateTime("2021-10-31", "09:40"); // different time
        assertTrue(now.isAfter(beforeDate));
        assertTrue(now.isAfter(beforeTime));

        // after the DateTime to test against -> returns false
        DateTime afterDate = new DateTime("2021-11-19", "13:20"); // different date
        DateTime afterTime = new DateTime("2021-10-31", "18:25"); // different time
        assertFalse(now.isAfter(afterDate));
        assertFalse(now.isAfter(afterTime));
    }

    @Test
    public void isBefore() {
        DateTime now = new DateTime("2021-10-31", "10:25");

        // before the DateTime to test against -> returns false
        DateTime beforeDate = new DateTime("2021-09-15", "13:00"); // different date
        DateTime beforeTime = new DateTime("2021-10-31", "09:40"); // different time
        assertFalse(now.isBefore(beforeDate));
        assertFalse(now.isBefore(beforeTime));

        // after the DateTime to test against -> returns true
        DateTime afterDate = new DateTime("2021-11-19", "13:20"); // different date
        DateTime afterTime = new DateTime("2021-10-31", "18:25"); // different time
        assertTrue(now.isBefore(afterDate));
        assertTrue(now.isBefore(afterTime));
    }

    @Test
    public void isOverdue() {

        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

        LocalDateTime before = now.minusMinutes(1);
        String[] dateTime = before.toString().split("T");
        String date = dateTime[0];
        String time = dateTime[1].substring(0, 5);

        // one minute before current time -> returns true
        assertTrue(DateTime.isOverdue(new DateTime(date, time)));

        // matching time (matching only minute and hour) -> returns false;
        dateTime = now.toString().split("T");
        date = dateTime[0];
        time = dateTime[1].substring(0, 5);

        assertFalse(DateTime.isOverdue(new DateTime(date, time)));

        // one minute after current time -> returns false
        LocalDateTime after = now.plusMinutes(1);
        dateTime = after.toString().split("T");
        date = dateTime[0];
        time = dateTime[1].substring(0, 5);

        assertFalse(DateTime.isOverdue(new DateTime(date, time)));

    }

    @Test
    public void incrementDateByDays() {
        DateTime before = new DateTime("2021-10-20", "12:30");
        DateTime after = new DateTime("2021-10-21", "12:30");

        //increment date by 1
        assertEquals(before.incrementDateByDays(1), after);
    }

    @Test
    public void incrementDateByWeeks() {
        DateTime before = new DateTime("2021-10-20", "12:30");
        DateTime after = new DateTime("2021-10-27", "12:30");

        //increment week by 1
        assertEquals(before.incrementDateByWeeks(1), after);
    }

    @Test
    public void compareTo() {
        DateTime dt0 = new DateTime("2021-12-25", "12:30"); // main DateTest item
        DateTime dt1 = new DateTime("2021-12-31", "23:59"); // compare with dt0 by time
        DateTime dt2 = new DateTime("2021-12-25", "12:00"); // compare with dt1 by date

        // dt0 is before dt1 -> returns negative value
        assertTrue(dt0.compareTo(dt1) < 0);

        // dt0 is after dt2 -> returns positive value
        assertTrue(dt0.compareTo(dt2) > 0);

        // happens at the same time -> returns 0
        assertFalse(dt0.compareTo(dt0) == -1);
    }
}
