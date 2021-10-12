package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidName));
    }

    @Test
    public void isValidEventName() {
        // null name
        assertThrows(NullPointerException.class, () -> EventName.isValidEventName(null));

        // invalid name
        assertFalse(EventName.isValidEventName("")); // empty string
        assertFalse(EventName.isValidEventName(" ")); // spaces only
        assertFalse(EventName.isValidEventName("^")); // only non-alphanumeric characters
        assertFalse(EventName.isValidEventName("sleep*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(EventName.isValidEventName("sleep and wake up")); // alphabets only
        assertTrue(EventName.isValidEventName("12345")); // numbers only
        assertTrue(EventName.isValidEventName("2nd time sleeping")); // alphanumeric characters
        assertTrue(EventName.isValidEventName("gO hOmE aH bOi")); // with capital letters

        // long names
        assertTrue(EventName.isValidEventName("Run for Charity sponsored by NUS CS2103T AY2122S1 T10 group 2"));
    }

    @Test
    public void testEquals() {
        EventName firstEventName = new EventName("first");
        EventName secondEventName = new EventName("second");

        // same EventName
        assertTrue(firstEventName.equals(firstEventName));

        // different EventName, same name
        EventName firstEventNameCopy = new EventName("first");
        assertTrue(firstEventName.equals(firstEventNameCopy));

        // null -> returns false
        assertFalse(firstEventName.equals(null));

        // different EventName, different name
        assertFalse(firstEventName.equals(secondEventName));
    }
}
