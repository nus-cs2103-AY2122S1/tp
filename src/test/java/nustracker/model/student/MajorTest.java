package nustracker.model.student;

import static nustracker.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MajorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Major(null));
    }

    @Test
    public void constructor_invalidMajor_throwsIllegalArgumentException() {
        String invalidMajor = "Computer Science";
        assertThrows(IllegalArgumentException.class, () -> new Major(invalidMajor));
    }

    @Test
    public void isValidMajor() {
        //null major
        assertThrows(NullPointerException.class, () -> new Major(null));

        //invalid majors
        assertFalse(Major.isValidMajor("Computer Sciences"));
        assertFalse(Major.isValidMajor("Information Systems"));
        assertFalse(Major.isValidMajor("Information Security"));
        assertFalse(Major.isValidMajor("Business Analytics"));
        assertFalse(Major.isValidMajor("CSS"));
        assertFalse(Major.isValidMajor("Systems"));
        assertFalse(Major.isValidMajor("InfoSec"));
        assertFalse(Major.isValidMajor("BA"));

        //valid majors
        assertTrue(Major.isValidMajor("CS"));
        assertTrue(Major.isValidMajor("IS"));
        assertTrue(Major.isValidMajor("ISEC"));
        assertTrue(Major.isValidMajor("BZA"));
    }

}
