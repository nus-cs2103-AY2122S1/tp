package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TutorialGroupTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TutorialGroup(null));
    }

    @Test
    public void constructor_invalidTutorialGroup_throwsIllegalArgumentException() {
        String invalidTutorialGroup = "B";
        assertThrows(IllegalArgumentException.class, () -> new TutorialGroup(invalidTutorialGroup));
    }

    @Test
    public void isValidTutorialGroup() {
        // null tutorial group
        assertThrows(NullPointerException.class, () -> TutorialGroup.isValidTutorialGroup(null));

        // invalid tutorial group
        assertFalse(TutorialGroup.isValidTutorialGroup("B")); // one character only
        assertFalse(TutorialGroup.isValidTutorialGroup("09")); // two digits only
        assertFalse(TutorialGroup.isValidTutorialGroup("T124")); // length exceeds maximum
        assertFalse(TutorialGroup.isValidTutorialGroup("t09")); // lowercase character

        // valid tutorial group
        assertTrue(TutorialGroup.isValidTutorialGroup("T09")); // any uppercase character and two digits
        assertTrue(TutorialGroup.isValidTutorialGroup("W20")); // any uppercase character and two digits
    }
}
