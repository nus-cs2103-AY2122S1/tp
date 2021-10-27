package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // blank description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // invalid description
        assertFalse(Description.isValidDescription("#$(*@^#(@*#^")); // all non-alphanumeric
        assertFalse(Description.isValidDescription("hello123213 &%$&%")); // some non-alphanumeric

        // valid description
        assertTrue(Description.isValidDescription("Hello hello")); // all alphabets
        assertTrue(Description.isValidDescription("12344556678790")); // all numbers
        assertTrue(Description.isValidDescription("KHSD90sdv1234346fSDG")); // numbers and alphabets
        assertTrue(Description.isValidDescription("9 8 7 6 4 34 3  5  8 7 7 ")); // numbers and spaces
        assertTrue(Description.isValidDescription("jgv u uf uiv ui v hv v")); // alphabets and spaces
        assertTrue(Description.isValidDescription("jhv 8 8g 7g 89v 8yv 97f")); // alphabets, numbers and spaces
    }
}
