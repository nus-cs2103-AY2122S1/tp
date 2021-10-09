package seedu.address.model.person;

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
        // null address
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid addresses
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary(" ")); // spaces only
        assertFalse(Salary.isValidSalary("a lot")); // non-numeric string

        // valid addresses
        assertTrue(Salary.isValidSalary("199999"));
        assertTrue(Salary.isValidSalary("0"));
    }
}
