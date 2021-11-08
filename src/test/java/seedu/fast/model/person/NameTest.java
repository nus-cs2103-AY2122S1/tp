package seedu.fast.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        String tooLongName = "this is a 51 character name that is not allowed hah";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
        assertThrows(IllegalArgumentException.class, () -> new Name(tooLongName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName(LengthUtil.EMPTY_STRING)); // empty string
        assertFalse(Name.isValidName(LengthUtil.WHITE_SPACE_STRING)); // spaces only
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
    public void hashcode() {
        Name standard = new Name("peter jack");
        Name nameWithSameValue = new Name("peter jack");
        Name nameWithDifferentValue = new Name("Capital Tan");

        assertTrue(standard.hashCode() == nameWithSameValue.hashCode());
        assertTrue(standard.hashCode() == standard.hashCode());

        assertFalse(standard.hashCode() == nameWithDifferentValue.hashCode());
    }
}
