package seedu.times.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.model.ModelTestUtils.LONG_STRING_120_CHAR;
import static seedu.times.model.ModelTestUtils.LONG_STRING_121_CHAR;
import static seedu.times.testutil.Assert.assertThrows;

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
        assertFalse(Name.isValidName(LONG_STRING_121_CHAR));

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName(LONG_STRING_120_CHAR));
    }

    @Test
    public void compareTo() {
        Name alice = new Name("Alice");
        Name bernard = new Name("Bernard");
        assertTrue(alice.compareTo(bernard) < 0);
        assertTrue(alice.compareTo(alice) == 0);
        assertTrue(bernard.compareTo(bernard) == 0);
        assertTrue(bernard.compareTo(alice) > 0);
    }
}
