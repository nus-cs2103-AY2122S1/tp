package seedu.address.model.tutorialgroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupType(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidGroupType = "";
        assertThrows(IllegalArgumentException.class, () -> new GroupType(invalidGroupType));
    }

    @Test
    public void parseGroupType() {
        String validGroupTypeOne = "OP1";
        String validGroupTypeTwo = "OP2";
        assertEquals(1, new GroupType(validGroupTypeOne).parseGroupType());
        assertEquals(2, new GroupType(validGroupTypeTwo).parseGroupType());
    }

    @Test
    public void isValidGroupType() {
        // null group type
        assertThrows(NullPointerException.class, () -> GroupType.isValidGroupType(null));

        // invalid group type
        assertFalse(GroupType.isValidGroupType("")); // empty string
        assertFalse(GroupType.isValidGroupType(" ")); // spaces only
        assertFalse(GroupType.isValidGroupType("OP3")); // invalid group type

        // valid group type
        assertTrue(GroupType.isValidGroupType("OP1")); // OP1 group
        assertTrue(GroupType.isValidGroupType("OP2")); // OP2 group
    }
}
