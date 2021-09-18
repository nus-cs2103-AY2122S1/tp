package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        assertFalse(EventDate.isValidDate("2021-9-1")); // wrong format
        assertFalse(EventDate.isValidDate("123")); // wrong format
        assertFalse(EventDate.isValidDate("2021 August 20")); // wrong format

        // valid dates
        assertTrue(EventDate.isValidDate("2021-08-01"));

    }
}
