package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void nameEqualsWorkAsExpected() {
        String alice = "Alice";
        Name testName = new Name(alice);
        Object objectName = (Object) testName;

        // Base tests to check equals method works.
        assertTrue(testName.equals(testName));
        assertTrue(testName.equals(new Name(alice)));

        assertTrue(testName.equals(new Name("alice"))); // Test all uncapitalized characters.
        assertTrue(testName.equals(new Name("ALICE"))); // Test all capitalized characters.
        assertTrue(testName.equals(new Name("aLiCe"))); // Test capitalizing characters at different places.
        assertTrue(testName.equals(objectName)); // Test different object.

        assertFalse(testName.equals(new Name("Alice "))); // Test with space bar.
        assertFalse(testName.equals(null)); // Test if testName handles null.
    }

    @Test
    public void nameToStringTest() {
        String alice = "Alice";
        Name testName = new Name(alice);

        assertTrue(testName.toString().equals("Alice"));
        assertFalse(testName.toString().equals("ALICE"));
    }

    @Test
    public void nameHashCodeTest() {
        String alice = "Alice";
        Name testName = new Name(alice);

        assertTrue(testName.hashCode() == (testName.fullName.hashCode()));
    }
}
