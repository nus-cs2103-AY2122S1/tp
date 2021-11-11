package nustracker.model.event;

import static nustracker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidEventName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidName));
    }

    @Test
    public void isValidEventName() {
        // null name
        assertThrows(NullPointerException.class, () -> EventName.isValidName(null));

        // invalid name
        Assertions.assertFalse(EventName.isValidName("")); // empty string
        Assertions.assertFalse(EventName.isValidName(" ")); // spaces only
        Assertions.assertFalse(EventName.isValidName("^")); // only non-alphanumeric characters
        Assertions.assertFalse(EventName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        Assertions.assertTrue(EventName.isValidName("peter jack")); // alphabets only
        Assertions.assertTrue(EventName.isValidName("12345")); // numbers only
        Assertions.assertTrue(EventName.isValidName("peter the 2nd")); // alphanumeric characters
        Assertions.assertTrue(EventName.isValidName("Capital Tan")); // with capital letters
        Assertions.assertTrue(EventName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

}
