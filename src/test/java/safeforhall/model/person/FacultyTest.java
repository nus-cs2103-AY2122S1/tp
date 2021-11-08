package safeforhall.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static safeforhall.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FacultyTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Faculty(null));
    }

    @Test
    public void constructor_invalidFaculty_throwsIllegalArgumentException() {
        String invalidName = "%aas";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidFaculty() {
        // null name
        assertThrows(NullPointerException.class, () -> Faculty.isValidFaculty(null));

        // invalid name
        assertFalse(Faculty.isValidFaculty("")); // empty string
        assertFalse(Faculty.isValidFaculty(" ")); // spaces only
        assertFalse(Faculty.isValidFaculty("^")); // only non-alphanumeric characters
        assertFalse(Faculty.isValidFaculty("peter*")); // contains non-alphanumeric characters
        assertFalse(Faculty.isValidFaculty("12345")); // numbers only
        assertFalse(Faculty.isValidFaculty("peter the 2nd")); // alphanumeric characters
        assertFalse(Faculty.isValidFaculty("David Roger Jackson Ray Jr 2nd")); // long names

        // valid name
        assertTrue(Faculty.isValidFaculty("SoC")); // alphabets only
        assertTrue(Faculty.isValidFaculty("Fass")); // with capital letters
    }

    @Test
    public void checkHashCode() {
        try {
            new Faculty("SoC").hashCode();
        } catch (NoSuchMethodError e) {
            fail();
        }
    }

    @Test
    public void checkCompareTo() {
        Faculty f1 = new Faculty("ABC");
        Faculty f2 = new Faculty("BCD");
        Faculty f3 = new Faculty("ABC");

        assertEquals(f1.compareTo(f2), -1);
        assertEquals(f2.compareTo(f1), 1);
        assertEquals(f3.compareTo(f1), 0);
    }
}
