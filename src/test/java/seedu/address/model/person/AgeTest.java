package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {
    @Test
    public void constructor_null_createsDefaultAge() {
        String noAge = "N.A";
        assertEquals(new Age(null).value, noAge);
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidAge = "Dinosaur";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
    }

    @Test
    public void isValidAge() {
        // invalid ages
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("ME")); // Contains non numbers
        assertFalse(Age.isValidAge("2one3")); // Contains non numbers
        assertFalse(Age.isValidAge("12.5")); // Contains decimal point

        // valid ages
        assertTrue(Age.isValidAge("10"));
        assertTrue(Age.isValidAge("2000"));
        assertTrue(Age.isValidAge("N.A"));
    }

    @Test
    public void equals() {

        Age age = new Age("10");

        // same object -> returns true
        assertTrue(age.equals(age));

        // same values -> returns true
        Age remarkCopy = new Age("10");
        assertTrue(age.equals(remarkCopy));

        // different types -> returns false
        assertFalse(age.equals(1));

        // null -> returns false
        assertFalse(age.equals(null));

        // different value -> returns false
        Age differentAge = new Age("11");
        assertFalse(age.equals(differentAge));
    }

    @Test
    public void isEmpty() {
        Age test = new Age(null);
        assertTrue(test.isEmpty());

        test = new Age("N.A");
        assertTrue(test.isEmpty());

        test = new Age("10101");
        assertFalse(test.isEmpty());
    }
}
