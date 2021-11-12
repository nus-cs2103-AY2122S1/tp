package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Status.translateStringToStatus(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> Status.translateStringToStatus(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null address
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid addresses
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus("single and ready to mingle")); // invalid status

        // valid addresses
        assertTrue(Status.isValidStatus("parttime"));
        assertTrue(Status.isValidStatus("nostatus")); // one character
    }
}
