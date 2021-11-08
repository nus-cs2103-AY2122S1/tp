package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.CARL;

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
    public void formattedNameTest() {
        String expectedName = "John Doe";

        Name n = new Name("john doe");
        assertEquals(expectedName, n.formatFullName());

        n = new Name("John doe");
        assertEquals(expectedName, n.formatFullName());

        n = new Name("john Doe");
        assertEquals(expectedName, n.formatFullName());

        n = new Name("jOhn doe");
        assertEquals(expectedName, n.formatFullName());

        n = new Name("john doE");
        assertEquals(expectedName, n.formatFullName());

        n = new Name("John       Doe");
        assertEquals(expectedName, n.formatFullName());
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
        assertFalse(Name.isValidName("Kenneth Hong Jia Wei Yi Guan Kleon Ang "
                + "Cunrong Liaw Xin Yan Chng Zi Hao")); // more than 50 characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("Kenneth Jia Wei Yi Guan Kleon Cunrong XinYan ZiHao")); // 50 char
        assertTrue(Name.isValidName("K")); // 1 char
    }

    @Test
    public void equals() {
        // same values -> returns true
        Name name = CARL.getName();
        Name nameCopy = new Name("Carl Kurz");
        assertTrue(name.equals(nameCopy));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different type -> returns false
        assertFalse(name.equals(5));

        // different name -> returns false
        Name nameDifferent = new Name("John");
        assertFalse(name.equals(nameDifferent));
    }
}
