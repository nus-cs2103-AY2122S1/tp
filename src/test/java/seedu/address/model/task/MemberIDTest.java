package seedu.address.model.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

class MemberIDTest {
    @Test
    public void constructor_emptyMemberID_throwsIllegalArgumentException() {
        String emptyMemberID = "";
        assertThrows(IllegalArgumentException.class, () -> new MemberID(emptyMemberID));
    }

    @Test
    public void constructor_invalidMemberID_throwsIllegalArgumentException() {
        String emptyMemberID = "abcd";
        assertThrows(IllegalArgumentException.class, () -> new MemberID(emptyMemberID));
    }

    @Test
    void isValidMemberID() {
        // null member id
        assertThrows(NullPointerException.class, () -> MemberID.isValidMemberID(null));

        // blank member id
        assertFalse(MemberID.isValidMemberID(""));
        assertFalse(MemberID.isValidMemberID(" "));

        // valid member id
        assertTrue(MemberID.isValidMemberID("12"));
        assertTrue(MemberID.isValidMemberID("1234"));
        assertTrue(MemberID.isValidMemberID("9999999"));
    }
}