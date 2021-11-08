package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void capitalise() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        assertEquals(Name.capitalise("a b c d e"), "A B C D E");
        assertEquals(Name.capitalise("alex"), "Alex");
        assertEquals(Name.capitalise("Alex"), "Alex");
        assertEquals(Name.capitalise("ALEX"), "Alex");
        assertEquals(Name.capitalise("aLEX"), "Alex");
        assertEquals(Name.capitalise("aLeX"), "Alex");
        assertEquals(Name.capitalise("AlEx"), "Alex");
        assertEquals(Name.capitalise("Alex  "), "Alex");
        assertEquals(Name.capitalise("CLIffOrd KAlEB toh jiNg zHe"), "Clifford Kaleb Toh Jing Zhe");
        assertEquals(Name.capitalise("lim  wei    chang"), "Lim Wei Chang"); //multiple spaces between names
    }
}
