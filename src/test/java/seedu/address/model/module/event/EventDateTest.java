package seedu.address.model.module.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.module.event.EventDate.isValidEventDate;

import org.junit.jupiter.api.Test;

public class EventDateTest {

    @Test
    public void test_eventDateNotValid_false() {
        assertFalse(isValidEventDate(""));
        assertFalse(isValidEventDate("hello"));
    }

    @Test
    public void test_eventDateOutOfBounds_false() {
        assertFalse(isValidEventDate("01/01/-1000"));
        assertFalse(isValidEventDate("31/12/1969"));
        assertFalse(isValidEventDate("01/01/3001"));
    }

    @Test
    public void test_eventDate_true() {
        assertTrue(isValidEventDate("31/11/1999"));
        assertTrue(isValidEventDate("29/02/1999"));
        assertTrue(isValidEventDate("17/09/1999"));
        assertTrue(isValidEventDate("01/01/1970"));
        assertTrue(isValidEventDate("31/12/3000"));
    }
}
