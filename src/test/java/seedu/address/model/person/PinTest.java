package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class PinTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Pin(null));
    }

    @Test
    public void constructor_invalidPin_throwsIllegalArgumentException() {
        String invalidPin = "";
        assertThrows(AssertionError.class, () -> new Pin(invalidPin));
    }

    @Test
    public void isValidPinStatus() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid addresses
        assertFalse(Pin.isValidPinStatus("")); // empty string
        assertFalse(Pin.isValidPinStatus(" ")); // spaces only

        // valid addresses
        assertTrue(Pin.isValidPinStatus("true"));
        assertTrue(Pin.isValidPinStatus("false")); // one character
    }

    @Test
    public void equals() {
        Pin firstPin = new Pin(true);
        Pin secondPin = new Pin(false);

        assertEquals(firstPin, firstPin);
        assertNotEquals(firstPin, secondPin);

        Pin firstPinCopy = new Pin(true);
        assertEquals(firstPinCopy, firstPin);
    }

    @Test
    public void unPinTest() {
        Pin firstPin = new Pin(true);
        Pin secondPin = new Pin(false);

        assertEquals(firstPin, secondPin.togglePin());
        assertEquals(secondPin, firstPin.togglePin());
    }
}
