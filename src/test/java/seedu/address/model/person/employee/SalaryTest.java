package seedu.address.model.person.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
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
        // null Salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid salary
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary(" ")); // spaces only
        assertFalse(Salary.isValidSalary("91")); // less than 3 numbers
        assertFalse(Salary.isValidSalary("invalid salary")); // non-numeric
        assertFalse(Salary.isValidSalary("90a20")); // alphabets within digits
        assertFalse(Salary.isValidSalary("9 000")); // spaces within digits

        // valid salary
        assertTrue(Salary.isValidSalary("800")); // exactly 3 numbers
        assertTrue(Salary.isValidSalary("5000")); // normal salary
        assertTrue(Salary.isValidSalary("100000")); // large salary
    }

    @Test
    public void equals() {
        Salary salary = new Salary(VALID_SALARY_AMY);

        // same values -> returns true
        Salary toCopy = new Salary(VALID_SALARY_AMY);
        assertTrue(salary.equals(toCopy));

        // same object -> returns true
        assertTrue(salary.equals(salary));

        // null -> returns false
        assertFalse(salary.equals(null));

        // different type -> returns false
        assertFalse(salary.equals(5));

        // different Salary -> returns false
        Salary different = new Salary(VALID_SALARY_BOB);
        assertFalse(salary.equals(different));

    }
}
