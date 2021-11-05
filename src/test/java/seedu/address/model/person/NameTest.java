package seedu.address.model.person;

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
        assertFalse(Name.isValidName("^")); // only non-alphabetic characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphabetic characters
        assertFalse(Name.isValidName("12345")); // contains non-alphabetic characters
        assertFalse(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // contains non-alphabetic characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters

    }

    @Test
    public void hashCode_validInput_correctOutput() {
        Name name = new Name("Jai");
        assertTrue(name.hashCode() == 74226);
    }

    @Test
    public void compareTo_sameNameSameCase_success() {
        Name name = new Name("Jai");
        Name sameCaseName = new Name("Jai");
        assertTrue(name.compareTo(sameCaseName) == 0);
    }

    @Test
    public void compareTo_sameNameDifferentCase_success() {
        Name name = new Name("Jai");
        Name differentCaseName = new Name("jAI");
        assertTrue(name.compareTo(differentCaseName) == 0);
    }

    @Test
    public void compareTo_differentName_success() {
        Name name = new Name("Jai");
        Name differentName = new Name("Atin");
        assertTrue(name.compareTo(differentName) != 0);
    }

    @Test
    public void equals_twoSameObjects_success() {
        Name name = new Name("Jai");
        assertTrue(name.equals(name));
    }

    @Test
    public void equals_twoDifferentObjects_falseOutput() {
        Name name = new Name("Jai");
        Email email = new Email("jay@gmail.com");
        assertFalse(name.equals(email));
    }

    @Test
    public void equals_twoDifferentObjectsWithSameName_success() {
        Name name1 = new Name("Jai");
        Name name2 = new Name("Jai");
        assertTrue(name1.equals(name2));
    }

    @Test
    public void equals_twoDifferentObjectsWithDifferentName_falseOutput() {
        Name name1 = new Name("Jai");
        Name name2 = new Name("Atin");
        assertFalse(name1.equals(name2));
    }

    @Test
    public void toString_aValidInput_success() {
        Name name = new Name("Jai");
        assertTrue(name.toString().equals("Jai"));
    }
}
