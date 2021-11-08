package seedu.smartnus.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class NoteNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new NoteName(invalidName));
    }

    @Test
    public void toString_validName_success() {
        String validName = "valid note";
        assertEquals(validName, new NoteName(validName).toString());
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> NoteName.isValidName(null));

        // invalid name
        assertFalse(NoteName.isValidName("")); // empty string
        assertFalse(NoteName.isValidName(" ")); // spaces only

        // valid name
        assertTrue(NoteName.isValidName("peter jack")); // alphabets only
        assertTrue(NoteName.isValidName("12345")); // numbers only
        assertTrue(NoteName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(NoteName.isValidName("Capital Tan")); // with capital letters
        assertTrue(NoteName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        NoteName alice = new NoteName("alice");
        NoteName bob = new NoteName("bob");
        assertNotEquals(alice, bob);

        NoteName aliceCopy = new NoteName("alice");
        assertEquals(alice, aliceCopy);

        //check if 2 different types of objects return false
        assertNotEquals(alice, 2);

        //check if object is equal to itself
        assertEquals(alice, alice);
    }

    @Test
    public void hashCode_test() {
        NoteName alice = new NoteName("alice");
        NoteName bob = new NoteName("bob");

        assertNotEquals(alice.hashCode(), bob.hashCode());

        NoteName aliceCopy = new NoteName("alice");
        assertEquals(alice.hashCode(), aliceCopy.hashCode());
    }
}
