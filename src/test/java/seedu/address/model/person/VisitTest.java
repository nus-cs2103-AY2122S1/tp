package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VisitTest {

    @Test
    public void equals() {
        Visit visit = new Visit("2021-11-11 12:00");

        // same object -> returns true
        assertTrue(visit.equals(visit));

        // same values -> returns true
        Visit visitCopy = new Visit(visit.value);
        assertTrue(visit.equals(visitCopy));

        // different types -> returns false
        assertFalse(visit.equals(1));

        // null -> returns false
        assertFalse(visit.equals(null));

        // different visit -> returns false
        Visit differentVisit = new Visit("2021-10-01 12:00");
        assertFalse(visit.equals(differentVisit));

        // non-empty visit -> returns true
        assertTrue(visit.hasVisit());

        // empty visit -> returns false
        Visit emptyVisit = new Visit("");
        assertFalse(emptyVisit.hasVisit());
    }

    @Test
    public void isValidVisit() {
        // correct time regex -> returns true
        assertTrue(Visit.isValidVisit("2021-01-02 12:00"));

        // correct time regex for 24hr HH -> returns true
        assertTrue(Visit.isValidVisit("2021-01-02 23:59"));

        // incorrect date format -> returns false
        assertFalse(Visit.isValidVisit("20210304 12:34"));

        // incorrect time format -> returns false
        assertFalse(Visit.isValidVisit("2021-04-05 0654"));

        // incorrect datetime format -> returns false
        assertFalse(Visit.isValidVisit("2021-04-0515:43"));

        // incorrect regex no time -> returns false
        assertFalse(Visit.isValidVisit("2021-01-02"));

        // incorrect regex no date -> returns false
        assertFalse(Visit.isValidVisit("12:01"));

        // incorrect year range regex -> return false
        assertFalse(Visit.isValidVisit("20211-01-02 12:00"));

        // incorrect year range regex -> return false
        assertFalse(Visit.isValidVisit("20211-01-02 12:00"));

        // incorrect month range regex -> return false
        assertFalse(Visit.isValidVisit("2021-13-02 12:00"));

        // incorrect day range regex -> return false
        assertFalse(Visit.isValidVisit("2021-01-32 12:00"));

        // incorrect hour range regex -> return false
        assertFalse(Visit.isValidVisit("2021-01-02 25:00"));

        // incorrect minute range regex -> return false
        assertFalse(Visit.isValidVisit("2021-01-02 12:99"));
    }

    @Test
    public void getFormatted() {
        // format displayed date
        String expectedDate = "01 Feb 2021 23:59";
        assertEquals(expectedDate, new Visit("2021-02-01 23:59").getFormatted());

        // format displayed date for empty visit
        assertEquals("", new Visit("").getFormatted());
    }
}
