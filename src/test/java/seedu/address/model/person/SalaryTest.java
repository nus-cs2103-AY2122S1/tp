package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SalaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void isValidSalary() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid salary
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary(" ")); // spaces only
        assertFalse(Salary.isValidSalary("a lot")); // non-numeric string
        assertFalse(Salary.isValidSalary("-500"));

        // valid salary
        assertTrue(Salary.isValidSalary("199999"));
        assertTrue(Salary.isValidSalary("0"));
    }

    @Test
    public void convertToDollars() {
        // rounding correctly
        Salary shouldNotRoundUp = new Salary("150");
        assertEquals("$1.50", shouldNotRoundUp.convertToDollars());

        // salary with single digit cents displayed correctly
        Salary singleDigitCents = new Salary("109");
        assertEquals("$1.09", singleDigitCents.convertToDollars());

        // salary with no cents is displayed correctly
        Salary zeroDigitCents = new Salary("100");
        assertEquals("$1.00", zeroDigitCents.convertToDollars());
    }
}
