package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.*;

import org.junit.jupiter.api.Test;

public class EventDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventDate(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new EventDate(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> EventDate.isValidDate(null));

        // invalid dates
        assertFalse(EventDate.isValidDate("")); // empty string
        assertFalse(EventDate.isValidDate("123")); // wrong format
        assertFalse(EventDate.isValidDate("123-10")); // wrong format
        assertFalse(EventDate.isValidDate("2021 August 20")); // wrong format
        assertFalse(EventDate.isValidDate("123-1-32")); // invalid day
        assertFalse(EventDate.isValidDate("0-13-1")); // invalid month
        assertFalse(EventDate.isValidDate("0-1-1")); // invalid year

        // valid dates
        assertTrue(EventDate.isValidDate("2021-08-31"));
        assertTrue(EventDate.isValidDate("2021-9-1"));
        assertTrue(EventDate.isValidDate("0001-9-1"));
        assertTrue(EventDate.isValidDate("1-9-1"));
        assertTrue(EventDate.isValidDate("21-9-1"));
        assertTrue(EventDate.isValidDate("121-9-1"));

    }
    
    @Test
    public void testEquals() {
        EventDate firstEventDate = new EventDate("2020-11-11");
        EventDate firstEventDateCopy = new EventDate("2020-11-11");
        EventDate secondEventDate = new EventDate("2020-11-12");

        // same EventDate
        assertTrue(firstEventDate.equals(firstEventDate));

        // different EventDate, same date
        assertTrue(firstEventDate.equals(firstEventDateCopy));

        // null -> returns false
        assertFalse(firstEventDate.equals(null));

        // different EventDate, different date
        assertFalse(firstEventDate.equals(secondEventDate));
    }
}
