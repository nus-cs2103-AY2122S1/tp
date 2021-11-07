package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class EventNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidEventName_throwsIllegalArgumentException() {
        String invalidName = ""; // empty string, boundary value
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidName));
    }

    @Test
    public void isValidEventName() {
        // null name
        assertThrows(NullPointerException.class, () -> EventName.isValidEventName(null));

        // invalid name
        assertFalse(EventName.isValidEventName("")); // empty string, boundary value
        assertFalse(EventName.isValidEventName(" ")); // spaces only, boundary value
        assertFalse(EventName.isValidEventName("^")); // only non-alphanumeric characters
        assertFalse(EventName.isValidEventName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(EventName.isValidEventName("peter jack")); // alphabets only
        assertTrue(EventName.isValidEventName("12345")); // numbers only
        assertTrue(EventName.isValidEventName("peter the 2nd")); // alphanumeric characters
        assertTrue(EventName.isValidEventName("Capital Tan")); // with capital letters
        assertTrue(EventName.isValidEventName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void copy() {
        EventName eventName = new EventName("Interesting Name");
        assertEquals(eventName, eventName.copy());
    }
}
