package seedu.intern.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ApplicationStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ApplicationStatus((String) null));
    }

    @Test
    public void constructor_invalidApplicationStatus_throwsIllegalArgumentException() {
        String invalidApplicationStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new ApplicationStatus(invalidApplicationStatus));
    }

    @Test
    public void isValidStatus() {
        // null name
        assertThrows(NullPointerException.class, () -> ApplicationStatus.isValidStatus(null));

        // invalid name
        assertFalse(ApplicationStatus.isValidStatus("")); // empty string
        assertFalse(ApplicationStatus.isValidStatus(" ")); // spaces only
        assertFalse(ApplicationStatus.isValidStatus("^")); // only non-alphanumeric characters
        assertFalse(ApplicationStatus.isValidStatus("peter*")); // contains non-alphanumeric characters
        assertFalse(ApplicationStatus.isValidStatus("denied")); // non keyword
        assertFalse(ApplicationStatus.isValidStatus("Accepted")); //Alphabets not capitalised

        // valid name
        assertTrue(ApplicationStatus.isValidStatus("ACCEPTED")); // keyword
        assertTrue(ApplicationStatus.isValidStatus("REJECTED")); // keyword
        assertTrue(ApplicationStatus.isValidStatus("APPLIED")); // keyword
        assertTrue(ApplicationStatus.isValidStatus("SCHEDULED")); // keyword
    }
}
