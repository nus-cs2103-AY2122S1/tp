package seedu.track2gather.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.track2gather.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.track2gather.model.person.attributes.CallStatus;

public class CallStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CallStatus(null));
    }

    @Test
    public void constructor_invalidCallStatus_throwsIllegalArgumentException() {
        String invalidCallStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new CallStatus(invalidCallStatus));
    }

    @Test
    public void isValidCallStatus() {
        // null
        assertThrows(NullPointerException.class, () -> CallStatus.isValidCallStatus(null));

        // blank call status
        assertFalse(CallStatus.isValidCallStatus("")); // empty string
        assertFalse(CallStatus.isValidCallStatus(" ")); // spaces only

        // missing fields
        assertFalse(CallStatus.isValidCallStatus("2")); // missing call status
        assertFalse(CallStatus.isValidCallStatus("false")); // missing numbers
        assertFalse(CallStatus.isValidCallStatus("true")); // missing numbers

        // invalid numbers
        assertFalse(CallStatus.isValidCallStatus("-1 false")); // negative integer
        assertFalse(CallStatus.isValidCallStatus("1.4 false")); // decimal
        assertFalse(CallStatus.isValidCallStatus("1a false")); // number with letters
        assertFalse(CallStatus.isValidCallStatus("1. true")); // decimal point
        assertFalse(CallStatus.isValidCallStatus("1 true ")); // white space back
        assertFalse(CallStatus.isValidCallStatus(" 1 true")); // white space front

        // invalid boolean value
        assertFalse(CallStatus.isValidCallStatus("2 fale")); // misspelled false
        assertFalse(CallStatus.isValidCallStatus("2 tre")); // misspelled true

        // valid call status
        assertTrue(CallStatus.isValidCallStatus("123456 false")); // 6 digit number
        assertTrue(CallStatus.isValidCallStatus("1 true")); // <6 digit number
        assertTrue(CallStatus.isValidCallStatus("0 false")); // default call status
    }
}
