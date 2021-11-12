package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsCalledTest {

    @Test
    public void equals() {
        IsCalled isCalled = new IsCalled("false");

        // same object -> returns true
        assertTrue(isCalled.equals(isCalled));

        // same values -> returns true
        IsCalled remarkCopy = new IsCalled(isCalled.value ? "TRUE" : "FALSE");
        assertTrue(isCalled.equals(remarkCopy));

        // different types -> returns false
        assertFalse(isCalled.equals(1));

        // null -> returns false
        assertFalse(isCalled.equals(null));

        // different remark -> returns false
        IsCalled differentIsCalled = new IsCalled("true");
        assertFalse(isCalled.equals(differentIsCalled));
    }

    @Test
    public void isValidIsCalled() {
        //true
        assertTrue(IsCalled.isValidIsCalled(""));
        assertTrue(IsCalled.isValidIsCalled("true"));
        assertTrue(IsCalled.isValidIsCalled("false"));
        assertTrue(IsCalled.isValidIsCalled("tRuE"));
        assertTrue(IsCalled.isValidIsCalled("FaLsE"));

        //false
        assertFalse(IsCalled.isValidIsCalled("this is not valid"));
        assertFalse(IsCalled.isValidIsCalled("1"));
        assertFalse(IsCalled.isValidIsCalled("f"));
        assertFalse(IsCalled.isValidIsCalled("t"));
    }
}
