package seedu.address.model.person.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FormClassTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FormClass(null));
    }

    @Test
    public void constructor_invalidFormClass_throwsIllegalArgumentException() {
        String invalidFormClass = "";
        assertThrows(IllegalArgumentException.class, () -> new FormClass(invalidFormClass));
    }

    @Test
    public void testEquals() {
        FormClass formClass = new FormClass("4E1");

        // null -> returns false
        assertFalse(formClass.equals(null));

        // same object -> returns true
        assertTrue(formClass.equals(formClass));

        // same values -> returns true
        FormClass formClassCopy = new FormClass("4E1");
        assertTrue(formClass.equals(formClassCopy));

        // different types -> returns false
        assertFalse(formClass.equals(10));

        // different MedicalHistory -> returns false
        FormClass formClassTwo = new FormClass("4E");
        assertFalse(formClass.equals(formClassTwo));
    }

    @Test
    public void isValidFormClass() {
        // null FormClass
        assertThrows(NullPointerException.class, () -> FormClass.isValidFormClass(null));

        // invalid FormClass
        assertFalse(FormClass.isValidFormClass("")); // empty string
        assertFalse(FormClass.isValidFormClass(" ")); // spaces only
        assertFalse(FormClass.isValidFormClass(" 4E1")); // contains whitespace at start
        assertFalse(FormClass.isValidFormClass("4@1")); // does not follow format
        assertFalse(FormClass.isValidFormClass("E1")); // does not start with 1-5
        assertFalse(FormClass.isValidFormClass("8E1")); // does not start with 1-5
        assertFalse(FormClass.isValidFormClass("4E_")); // contain non alphanumeric
        assertFalse(FormClass.isValidFormClass("1E ")); // contain non alphanumeric

        // valid FormClass
        assertTrue(FormClass.isValidFormClass("4e1")); // in the correct format
        assertTrue(FormClass.isValidFormClass("2e")); // Without optional alphanumeric
        assertTrue(FormClass.isValidFormClass("4E1")); // with capital letters
        assertTrue(FormClass.isValidFormClass("4Harmony1")); // long names
        assertTrue(FormClass.isValidFormClass("1N12")); // more than digit at start and end
        assertTrue(FormClass.isValidFormClass("1Science4Life")); // alphanumeric mix
    }
}
