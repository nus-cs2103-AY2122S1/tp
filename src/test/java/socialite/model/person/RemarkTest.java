package socialite.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void equals() {
        Remark remark = new Remark("Hello");

        // same object -> returns true
        assertEquals(remark, remark);

        // same values -> returns true
        Remark remarkCopy = new Remark(remark.get());
        assertEquals(remark, remarkCopy);

        // different types -> returns false
        assertNotEquals(1, remark);

        // null -> returns false
        assertNotEquals(null, remark);

        // different remark -> returns false
        Remark differentRemark = new Remark("Bye");
        assertNotEquals(remark, differentRemark);
    }
}
