package seedu.address.model.person.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_EMAIL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_PHONE;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.INVALID_SORT_BY_SHIFT;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.INVALID_SORT_BY_TAG;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_SORT_BY_JOB_TITLE;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_SORT_BY_LEAVES;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_SORT_BY_SALARY;

import org.junit.jupiter.api.Test;

public class SortByEmployeeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortByEmployee(null));
    }

    @Test
    public void constructor_invalidSortBy_throwsIllegalArgumentException() {
        String invalidSortBy = "";
        assertThrows(IllegalArgumentException.class, () -> new SortByEmployee(invalidSortBy));
    }

    @Test
    public void isValidSortBy() {
        // null
        assertThrows(NullPointerException.class, () -> SortByEmployee.isValidSortBy(null));

        // invalid sorting orders
        assertFalse(SortByEmployee.isValidSortBy("")); // empty string
        assertFalse(SortByEmployee.isValidSortBy(" ")); // spaces only
        assertFalse(SortByEmployee.isValidSortBy("123")); // not valid prefix
        assertFalse(SortByEmployee.isValidSortBy("hello")); // not valid prefix
        assertFalse(SortByEmployee.isValidSortBy("t")); // cannot sort by tags
        assertFalse(SortByEmployee.isValidSortBy("sh")); // cannot sort by shifts

        // valid sorting orders
        assertTrue(SortByEmployee.isValidSortBy("n")); // Sort by name
        assertTrue(SortByEmployee.isValidSortBy("a")); // Sort by address
        assertTrue(SortByEmployee.isValidSortBy("e")); // Sort by email
        assertTrue(SortByEmployee.isValidSortBy("p")); // Sort by phone number
        assertTrue(SortByEmployee.isValidSortBy("l")); // Sort by leaves
        assertTrue(SortByEmployee.isValidSortBy("jt")); // Sort by job title
        assertTrue(SortByEmployee.isValidSortBy("sal")); // Sort by sal
    }

    @Test
    public void toStringTest() {
        SortByEmployee sortBy = new SortByEmployee(VALID_SORT_BY_ADDRESS);
        assertTrue(sortBy.toString().equals("address"));

        sortBy = new SortByEmployee(VALID_SORT_BY_EMAIL);
        assertTrue(sortBy.toString().equals("email"));

        sortBy = new SortByEmployee(VALID_SORT_BY_PHONE);
        assertTrue(sortBy.toString().equals("phone"));

        sortBy = new SortByEmployee(VALID_SORT_BY_NAME);
        assertTrue(sortBy.toString().equals("name"));

        sortBy = new SortByEmployee(VALID_SORT_BY_LEAVES);
        assertTrue(sortBy.toString().equals("leaves"));

        sortBy = new SortByEmployee(VALID_SORT_BY_SALARY);
        assertTrue(sortBy.toString().equals("salary"));

        sortBy = new SortByEmployee(VALID_SORT_BY_JOB_TITLE);
        assertTrue(sortBy.toString().equals("job title"));

        String invalidSortBy = "1234abc";
        assertThrows(IllegalArgumentException.class, () -> new SortByEmployee(invalidSortBy).toString());

        // Sorting by shift is invalid
        assertThrows(IllegalArgumentException.class, () -> new SortByEmployee(INVALID_SORT_BY_SHIFT).toString());

        // Sorting by tag is invalid
        assertThrows(IllegalArgumentException.class, () -> new SortByEmployee(INVALID_SORT_BY_TAG).toString());
    }

    @Test
    public void equals() {
        SortByEmployee sortBy = new SortByEmployee(VALID_SORT_BY_LEAVES);

        // same values -> returns true
        SortByEmployee toCopy = new SortByEmployee(VALID_SORT_BY_LEAVES);
        assertTrue(sortBy.equals(toCopy));

        // same object -> returns true
        assertTrue(sortBy.equals(sortBy));

        // null -> returns false
        assertFalse(sortBy.equals(null));

        // different type -> returns false
        assertFalse(sortBy.equals(5));

        // different SortBy -> returns false
        SortByEmployee different = new SortByEmployee(VALID_SORT_BY_JOB_TITLE);
        assertFalse(sortBy.equals(different));

        different = new SortByEmployee(VALID_SORT_BY_ADDRESS);
        assertFalse(sortBy.equals(different));

        different = new SortByEmployee(VALID_SORT_BY_NAME);
        assertFalse(sortBy.equals(different));

        different = new SortByEmployee(VALID_SORT_BY_EMAIL);
        assertFalse(sortBy.equals(different));

        different = new SortByEmployee(VALID_SORT_BY_PHONE);
        assertFalse(sortBy.equals(different));

        different = new SortByEmployee(VALID_SORT_BY_SALARY);
        assertFalse(sortBy.equals(different));
    }
}
