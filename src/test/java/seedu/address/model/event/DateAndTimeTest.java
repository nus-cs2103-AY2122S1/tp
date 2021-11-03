package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class DateAndTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateAndTime(null));
    }

    @Test
    public void constructor_invalidZoomLink_throwsIllegalArgumentException() {
        String emptyDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new DateAndTime(emptyDateTime));
    }

    @Test
    public void containsString() {
        DateAndTime dateAndTime = new DateAndTime("01-12-2012 11:22");

        // keywords contained in dateAndTime
        List<String> listOfKeywordsContained = Arrays.asList("01-10-20", "11:");
        assertTrue(dateAndTime.containsString(listOfKeywordsContained));

        //keywords not contained in dateAndTime
        List<String> noKeywordsContained = Arrays.asList("morning", "11:3", "11:21");
        assertFalse(dateAndTime.containsString(noKeywordsContained));
    }

    @Test
    public void isValidDateAndTime() {
        // null DateAndTime
        assertThrows(NullPointerException.class, () -> DateAndTime.isValidDateTime(null));

        // blank DateAndTime
        assertFalse(DateAndTime.isValidDateTime("")); // empty string
        assertFalse(DateAndTime.isValidDateTime(" ")); // spaces only

        // missing parts
        assertFalse(DateAndTime.isValidDateTime("11-10-2021")); // missing time
        assertFalse(DateAndTime.isValidDateTime("11-10 12:10")); // missing year
        assertFalse(DateAndTime.isValidDateTime("11-10")); // missing year and time

        // invalid parts
        assertFalse(DateAndTime.isValidDateTime("2010/12/21 12:10")); // invalid connector
        assertFalse(DateAndTime.isValidDateTime("2012-12-20 12:10")); // wrong order
        assertFalse(DateAndTime.isValidDateTime("01-13-2012")); // wrong order of date and month
        assertFalse(DateAndTime.isValidDateTime(" 01-13-2012 12:05")); // leading space
        assertFalse(DateAndTime.isValidDateTime("01-13-2012 12:05 ")); // trailing space
        assertFalse(DateAndTime.isValidDateTime("2021 Oct 2 12:10")); // wrong date format
        assertFalse(DateAndTime.isValidDateTime("%01-13-2012 10:15")); // include invalid symbol '%'
        assertFalse(DateAndTime.isValidDateTime("01-13-2012 11:15pm")); // invalid time format
        assertFalse(DateAndTime.isValidDateTime("1-13-2012 11:15")); // missing leading 0
        assertFalse(DateAndTime.isValidDateTime("01-12-20001 11:00")); // year can only take in 4-digit numbers
        assertFalse(DateAndTime.isValidDateTime("31-02-2000 11:00")); // 31 Feb is not a valid date
        assertFalse(DateAndTime.isValidDateTime("01-5-2021 10:00")); // missing leading 0 for month input

        // valid DateAndTime
        assertTrue(DateAndTime.isValidDateTime("01-12-2021 11:00"));
        assertTrue(DateAndTime.isValidDateTime("31-12-1090 10:05"));
        assertTrue(DateAndTime.isValidDateTime("01-02-2012 11:22"));
        assertTrue(DateAndTime.isValidDateTime("01-12-9000 11:00")); // year can take in any 4 digits number
    }

    @Test
    public void isBefore() {
        DateAndTime firstDateTime = new DateAndTime("30-09-2021 22:50");
        DateAndTime secondDateTime = new DateAndTime("30-09-2021 22:55");
        DateAndTime thirdDateTime = new DateAndTime("30-10-2021 02:30");

        assertTrue(firstDateTime.isBefore(secondDateTime));
        assertTrue(firstDateTime.isBefore(thirdDateTime));
        assertFalse(thirdDateTime.isBefore(secondDateTime));
    }

    @Test
    public void compareTo() {
        DateAndTime firstDateTime = new DateAndTime("30-09-2021 22:50");
        DateAndTime secondDateTime = new DateAndTime("30-09-2021 22:55");
        DateAndTime secondDuplicate = new DateAndTime("30-09-2021 22:55");
        DateAndTime thirdDateTime = new DateAndTime("30-10-2021 02:30");

        assertTrue(firstDateTime.compareTo(secondDateTime) < 0);
        assertTrue(firstDateTime.compareTo(thirdDateTime) < 0);
        assertTrue(thirdDateTime.compareTo(firstDateTime) > 0);
        assertEquals(0, secondDateTime.compareTo(secondDuplicate));
    }
}

