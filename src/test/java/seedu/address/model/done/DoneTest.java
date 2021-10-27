package seedu.address.model.done;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Role;

public class DoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Done(null));
    }

    @Test
    public void constructor_invalidDone_throwsIllegalArgumentException() {
        String invalidDone = ""; // A doneStatus can only be "Done" or "Not Done"
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidDone));
    }

    @Test
    public void testSetAsDone() {
        String validDone = "Done";
        Done testDone = new Done("Not Done");
        testDone.setAsDone();
        assertEquals(validDone, testDone.getDoneStatus());
    }

    @Test
    public void testSetAsUndone() {
        String validDone = "Not Done";
        Done testDone = new Done("Done");
        testDone.setAsUndone();
        assertEquals(validDone, testDone.getDoneStatus());
    }

}
