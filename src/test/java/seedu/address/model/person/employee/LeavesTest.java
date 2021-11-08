package seedu.address.model.person.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_LEAVES_AMY;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_LEAVES_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LeavesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Leaves(null));
    }

    @Test
    public void constructor_invalidLeaves_throwsIllegalArgumentException() {
        String invalidLeaves = "";
        assertThrows(IllegalArgumentException.class, () -> new Leaves(invalidLeaves));
    }

    @Test
    public void isValidLeaves() {
        // invalid leaves
        assertFalse(Leaves.isValidLeaves(null)); // null
        assertFalse(Leaves.isValidLeaves("")); // empty string
        assertFalse(Leaves.isValidLeaves(" ")); // spaces only
        assertFalse(Leaves.isValidLeaves("Leaves")); // non-numeric
        assertFalse(Leaves.isValidLeaves("9011p041")); // alphabets within digits
        assertFalse(Leaves.isValidLeaves("9312 1534")); // spaces within digits

        // valid leaves
        assertTrue(Leaves.isValidLeaves("9")); // have 1 number
        assertTrue(Leaves.isValidLeaves("14")); // have 2 numbers
        assertTrue(Leaves.isValidLeaves("100")); // have 3 numbers
    }

    @Test
    public void equals() {
        Leaves leaves = new Leaves(VALID_LEAVES_AMY);

        // same values -> returns true
        Leaves toCopy = new Leaves(VALID_LEAVES_AMY);
        assertTrue(leaves.equals(toCopy));

        // same object -> returns true
        assertTrue(leaves.equals(leaves));

        // null -> returns false
        assertFalse(leaves.equals(null));

        // different type -> returns false
        assertFalse(leaves.equals(5));

        // different Leaves -> returns false
        Leaves different = new Leaves(VALID_LEAVES_BOB);
        assertFalse(leaves.equals(different));

    }
}

