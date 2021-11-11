package nustracker.model.student;

import static nustracker.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class YearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Year(null));
    }

    @Test
    public void constructor_invalidYear_throwsInvalidArgumentException() {
        String invalidYear = "Sophomore";
        assertThrows(IllegalArgumentException.class, () -> new Major(invalidYear));
    }

    @Test
    public void isValidYear() {
        //Null year
        assertThrows(NullPointerException.class, () -> new Year(null));

        //Invalid years
        assertFalse(Year.isValidYear("First"));
        assertFalse(Year.isValidYear("Second"));
        assertFalse(Year.isValidYear("Third"));
        assertFalse(Year.isValidYear("Fourth"));
        assertFalse(Year.isValidYear("Fifth"));
        assertFalse(Year.isValidYear("Sixth"));
        assertFalse(Year.isValidYear("One"));
        assertFalse(Year.isValidYear("Two"));
        assertFalse(Year.isValidYear("Three"));
        assertFalse(Year.isValidYear("Four"));
        assertFalse(Year.isValidYear("Five"));
        assertFalse(Year.isValidYear("Six"));

        //Valid years
        assertTrue(Year.isValidYear("1"));
        assertTrue(Year.isValidYear("2"));
        assertTrue(Year.isValidYear("3"));
        assertTrue(Year.isValidYear("4"));
        assertTrue(Year.isValidYear("5"));
        assertTrue(Year.isValidYear("6"));
    }

}
