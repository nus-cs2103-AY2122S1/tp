package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExpectedSalaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpectedSalary(null));
    }

    @Test
    public void constructor_invalidExpectedSalary_throwsIllegalArgumentException() {
        String invalidExpectedSalary = "";
        assertThrows(IllegalArgumentException.class, () -> new ExpectedSalary(invalidExpectedSalary));
    }

    @Test
    public void isValidExpectedSalary() {
        // null expected salary
        assertThrows(NullPointerException.class, () -> ExpectedSalary.isValidExpectedSalary(null));

        // invalid expected salary
        assertFalse(ExpectedSalary.isValidExpectedSalary("")); // empty string
        assertFalse(ExpectedSalary.isValidExpectedSalary(" ")); // spaces only
        assertFalse(ExpectedSalary.isValidExpectedSalary("-1")); // negative number
        assertFalse(ExpectedSalary.isValidExpectedSalary("phone")); // non-numeric
        assertFalse(ExpectedSalary.isValidExpectedSalary("9011p041")); // alphabets within digits
        assertFalse(ExpectedSalary.isValidExpectedSalary("!@#$%^&*()")); // non-alphanumeric characters
        assertFalse(ExpectedSalary.isValidExpectedSalary("9312 1534")); // spaces within digits
        assertFalse(ExpectedSalary.isValidExpectedSalary("124293842033123")); // long expected salary

        // valid expected salary
        assertTrue(ExpectedSalary.isValidExpectedSalary("0")); // exactly 1 number
        assertTrue(ExpectedSalary.isValidExpectedSalary("911")); // more than 1 number
        assertTrue(ExpectedSalary.isValidExpectedSalary("93121534"));

    }
}
