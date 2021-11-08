package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

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

        // long name
        assertFalse(Name.isValidName("123456789012345678901234567890123456789012345678901234567890123"
                + "456789012345678901234567890"));
    }

    @Test
    public void isEqualName() {
        Name name = new Name("peter jack");
        Name differentName = new Name("David Roger Jackson Ray Jr 2nd");
        Name sameName = new Name("peter jack");
        Person person = ALICE;

        //Different Objects
        assertFalse(name.equals(person));

        // Different Name
        assertFalse(name.equals(differentName));

        // Same Object
        assertTrue(name.equals(name));

        // Different Objects Same Name
        assertTrue(name.equals(sameName));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(VALID_NAME_AMY.hashCode(), VALID_NAME_AMY.hashCode());
        assertNotEquals(VALID_NAME_AMY.hashCode(), VALID_NAME_BOB.hashCode());
    }
}
