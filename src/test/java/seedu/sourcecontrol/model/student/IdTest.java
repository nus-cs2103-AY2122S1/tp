package seedu.sourcecontrol.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sourcecontrol.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ID(null));
    }

    @Test
    public void constructor_invalidID_throwsIllegalArgumentException() {
        String invalidID = "";
        assertThrows(IllegalArgumentException.class, () -> new ID(invalidID));
    }

    @Test
    public void getValue() {
        assertEquals(new ID("E1234567").getValue(), "E1234567");

        // correctly format to capital letter
        assertEquals(new ID("e1234567").getValue(), "E1234567");
    }

    @Test
    public void isValidID() {
        // null ID number
        assertThrows(NullPointerException.class, () -> ID.isValidID(null));

        // invalid ID numbers
        assertFalse(ID.isValidID("")); // empty string
        assertFalse(ID.isValidID(" ")); // spaces only
        assertFalse(ID.isValidID("1231231")); // missing starting character
        assertFalse(ID.isValidID("A123 456")); // wrong starting character
        assertFalse(ID.isValidID("E12345678")); // exceed length
        assertFalse(ID.isValidID("E123456")); // below required length
        assertFalse(ID.isValidID("E123m231")); // alphabets within digits
        assertFalse(ID.isValidID("E123 456")); // spaces within digits

        // valid ID numbers
        assertTrue(ID.isValidID("E1234567")); // upper case
        assertTrue(ID.isValidID("e7654321")); // lower case
    }

}
