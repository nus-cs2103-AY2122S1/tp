package seedu.address.model.tutorialgroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupNumber(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidGroupNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new GroupNumber(invalidGroupNumber));
    }

    @Test
    public void parseGroupNumber() {
        String validGroupNumberOne = "1";
        String validGroupNumberTwo = "2";
        assertEquals(1, new GroupNumber(validGroupNumberOne).parseGroupNumber());
        assertEquals(2, new GroupNumber(validGroupNumberTwo).parseGroupNumber());
    }

    @Test
    public void isValidGroupNumber() {
        // null group number
        assertThrows(NullPointerException.class, () -> GroupNumber.isValidGroupNumber(null));

        // invalid group number
        assertFalse(GroupNumber.isValidGroupNumber("")); // empty string
        assertFalse(GroupNumber.isValidGroupNumber(" ")); // spaces only
        assertFalse(GroupNumber.isValidGroupNumber("^")); // only non-alphanumeric characters
        assertFalse(GroupNumber.isValidGroupNumber("peter*")); // contains non-alphanumeric characters
        assertFalse(GroupNumber.isValidGroupNumber("peter")); // contains alphabets
        assertFalse(GroupNumber.isValidGroupNumber("12")); // contains more than one digit
        assertFalse(GroupNumber.isValidGroupNumber("-1")); // negative number
        assertFalse(GroupNumber.isValidGroupNumber("0")); // invalid group number

        // valid group number
        assertTrue(GroupNumber.isValidGroupNumber("1")); // single-digit numbers only
        assertTrue(GroupNumber.isValidGroupNumber("2")); // single-digit numbers only
        assertTrue(GroupNumber.isValidGroupNumber("3")); // single-digit numbers only
        assertTrue(GroupNumber.isValidGroupNumber("4")); // single-digit numbers only
    }
}
