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
        assertFalse(TutorialGroup.isValidTutorialGroup("BEA")); // no digits
        assertFalse(TutorialGroup.isValidTutorialGroup("B09")); // first letter is not M/T/W/F

        // valid tutorial group
        assertTrue(TutorialGroup.isValidTutorialGroup("M09")); // M/T/W/F and two digits
        assertTrue(TutorialGroup.isValidTutorialGroup("T09")); // M/T/W/F and two digits
        assertTrue(TutorialGroup.isValidTutorialGroup("W20")); // M/T/W/F and two digits
        assertTrue(TutorialGroup.isValidTutorialGroup("F20")); // M/T/W/F and two digits
        assertTrue(TutorialGroup.isValidTutorialGroup("m12")); // m/t/w/f and two digits
        assertTrue(TutorialGroup.isValidTutorialGroup("t09")); // m/t/w/f and two digits
        assertTrue(TutorialGroup.isValidTutorialGroup("w09")); // m/t/w/f and two digits
        assertTrue(TutorialGroup.isValidTutorialGroup("f10")); // m/t/w/f and two digits
    }
}
