package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DueDateTest {

    @Test
    public void constructor_invalidDueDate_throwsIllegalArgumentException() {
        String invalidDueDate = "";
        assertThrows(IllegalArgumentException.class, () -> new DueDate(invalidDueDate));
    }

    @Test
    public void isValidDate() {
        // null Date
        assertThrows(NullPointerException.class, () -> DueDate.isValidDate(null));

        // invalid DueDate
        assertFalse(DueDate.isValidDate("")); // empty string
        assertFalse(DueDate.isValidDate(" ")); // spaces only
        assertFalse(DueDate.isValidDate("32/12/2021")); // invalid day
        assertFalse(DueDate.isValidDate("00/12/2021")); // invalid day
        assertFalse(DueDate.isValidDate("-1/12/2021")); // invalid day
        assertFalse(DueDate.isValidDate("12/13/2021")); // invalid month
        assertFalse(DueDate.isValidDate("12/-1/2021")); // invalid month
        assertFalse(DueDate.isValidDate("12/00/2021")); // invalid month
        assertFalse(DueDate.isValidDate("12/10/0000")); // invalid year
        assertFalse(DueDate.isValidDate("12/10/-0001")); // invalid year
        assertFalse(DueDate.isValidDate("12/10/10000")); // invalid year
        assertFalse(DueDate.isValidDate("29/02/2021")); // invalid day for non-leap year
        assertFalse(DueDate.isValidDate("31/02/2021")); // invalid day for non-leap year
        assertFalse(DueDate.isValidDate("31/04/2021")); // invalid day for april
        assertFalse(DueDate.isValidDate("31/06/2021")); // invalid day for june
        assertFalse(DueDate.isValidDate("31/09/2021")); // invalid day for september
        assertFalse(DueDate.isValidDate("31/11/2021")); // invalid day for november

        // valid DueDate
        assertTrue(DueDate.isValidDate("11/11/2021")); // double-digit for day and month
        assertTrue(DueDate.isValidDate("1/1/2021")); // single digit for day and month
        assertTrue(DueDate.isValidDate("1/11/2021")); // single digit for day and double-digit for month
        assertTrue(DueDate.isValidDate("11/1/2021")); // double-digit for day and double-digit for month
        assertTrue(DueDate.isValidDate("31/12/9999")); // maximum values for day, month and year
        assertTrue(DueDate.isValidDate("01/01/0001")); // minimum values for day, month and year
        assertTrue(DueDate.isValidDate("01/02/2021")); // day and month with prefix 0
        assertTrue(DueDate.isValidDate("01/1/2021")); // day with prefix 0 with single digit month
        assertTrue(DueDate.isValidDate("1/01/2021")); // month with prefix 0 with single digit day
        assertTrue(DueDate.isValidDate("11/01/2021")); // month with prefix 0 with double-digit day
        assertTrue(DueDate.isValidDate("01/11/2021")); // day with prefix 0 with double-digit month
        assertTrue(DueDate.isValidDate("29/02/2024")); // leap year
        assertTrue(DueDate.isValidDate("29/2/2060")); // leap year
    }

    @Test
    public void isValidTime() {
        // null Date
        assertThrows(NullPointerException.class, () -> DueDate.isValidTime(null));

        // invalid DueDate
        assertFalse(DueDate.isValidDate("")); // empty string
        assertFalse(DueDate.isValidDate(" ")); // spaces only
        assertFalse(DueDate.isValidTime("2400")); // invalid hour
        assertFalse(DueDate.isValidTime("0060")); // invalid minutes
        assertFalse(DueDate.isValidTime("2460")); // invalid hour and minutes

        // valid DueDate
        assertTrue(DueDate.isValidTime("2359")); // maximum hour and minutes
        assertTrue(DueDate.isValidTime("0000")); // minimum hour and minutes
        assertTrue(DueDate.isValidTime("0059")); // minimum hour and maximum minutes
        assertTrue(DueDate.isValidTime("2300")); // maximum hour and minimum minutes
    }


    @Test
    public void isValidDueDate() {
        // null dueDate
        assertThrows(NullPointerException.class, () -> DueDate.isValidDueDate(null));

        // invalid dueDate
        assertFalse(DueDate.isValidDueDate("")); // empty string
        assertFalse(DueDate.isValidDueDate(" ")); // spaces only
        assertFalse(DueDate.isValidDueDate("^")); // only non-alphanumeric characters
        assertFalse(DueDate.isValidDueDate("11/11/2021*")); // contains non-alphanumeric characters
        assertFalse(DueDate.isValidDueDate("11/11/2021,1588")); // contains invalid time
        assertFalse(DueDate.isValidDueDate("111/188/2021*")); // contains invalid day and month
        assertFalse(DueDate.isValidDueDate("11/11/2021 1444")); // no comma but space between time and date
        assertFalse(DueDate.isValidDueDate("11/11/2021,744")); // invalid format for time
        assertFalse(DueDate.isValidDueDate("00/11/2023,0744")); // invalid day
        assertFalse(DueDate.isValidDueDate("-1/11/2023,0744")); // invalid day
        assertFalse(DueDate.isValidDueDate("11/00/2023,0744")); // invalid month
        assertFalse(DueDate.isValidDueDate("11/-1/2023,0744")); // invalid month
        assertFalse(DueDate.isValidDueDate("11/11/0000,0744")); // invalid year
        assertFalse(DueDate.isValidDueDate("11/11/-0001,0744")); // invalid year
        assertFalse(DueDate.isValidDueDate("31/04/2021,0744")); // invalid day for april
        assertFalse(DueDate.isValidDueDate("31/06/2021,0744")); // invalid day for june
        assertFalse(DueDate.isValidDueDate("31/09/2021,0744")); // invalid day for september
        assertFalse(DueDate.isValidDueDate("31/11/2021,0744")); // invalid day for november
        assertFalse(DueDate.isValidDueDate("29/02/2021,0744")); // invalid day for non-leap year
        assertFalse(DueDate.isValidDueDate("31/02/2021,0744")); // invalid day for non-leap year

        // valid dueDate
        assertTrue(DueDate.isValidDueDate("11/11/2021")); // date only
        assertTrue(DueDate.isValidDueDate("02/02/2011,1650")); // prefix 0 for day and month
        assertTrue(DueDate.isValidDueDate("11/1/2021,1541")); // single digit for month
        assertTrue(DueDate.isValidDueDate("1/11/2021,1400")); // single digit for day
        assertTrue(DueDate.isValidDueDate("29/02/2024,2100")); // leap year
        assertTrue(DueDate.isValidDueDate("29/2/2064,2100")); // leap year

    }

    @Test
    public void isValidFriendlyDate() {
        // Valid friendly dates
        assertTrue(DueDate.isValidFriendlyDate("mon"));
        assertTrue(DueDate.isValidFriendlyDate("tue"));
        assertTrue(DueDate.isValidFriendlyDate("wed"));
        assertTrue(DueDate.isValidFriendlyDate("thu"));
        assertTrue(DueDate.isValidFriendlyDate("fri"));
        assertTrue(DueDate.isValidFriendlyDate("sat"));
        assertTrue(DueDate.isValidFriendlyDate("sun"));
        assertTrue(DueDate.isValidFriendlyDate("tmr"));
        assertTrue(DueDate.isValidFriendlyDate("today"));
        assertTrue(DueDate.isValidFriendlyDate("week"));

        // Invalid friendly dates
        assertFalse(DueDate.isValidFriendlyDate("not a date"));
        assertFalse(DueDate.isValidFriendlyDate("123"));
        assertFalse(DueDate.isValidFriendlyDate(""));
        assertThrows(NullPointerException.class, () -> DueDate.isValidFriendlyDate(null));
    }

    @Test
    public void isValidFriendlyDateAndTime() {

        // valid friendly date and time
        assertTrue(DueDate.isValidFriendlyDateAndTime("tmr,2359")); // tmr and maximum time
        assertTrue(DueDate.isValidFriendlyDateAndTime("week,1200")); // week and middle time
        assertTrue(DueDate.isValidFriendlyDateAndTime("mon,0700")); // mon and morning
        assertTrue(DueDate.isValidFriendlyDateAndTime("fri,2234")); // fri and night
        assertTrue(DueDate.isValidFriendlyDateAndTime("today,0000")); // today and minimum time

        // invalid friendly date and time
        assertFalse(DueDate.isValidFriendlyDateAndTime("tmr, 2359")); // space after comma
        assertFalse(DueDate.isValidFriendlyDateAndTime("tomorrow,2359")); // invalid friendly format
        assertFalse(DueDate.isValidFriendlyDateAndTime("02/02/2021,1000")); // valid date but invalid friendly date
        assertFalse(DueDate.isValidFriendlyDateAndTime(",1000")); // missing friendly command

    }
}
