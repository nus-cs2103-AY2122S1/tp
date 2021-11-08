package seedu.address.model.person.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_JOB_TITLE_AMY;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobTitle(null));
    }

    @Test
    public void constructor_invalidJobTitle_throwsIllegalArgumentException() {
        String invalidJobTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new JobTitle(invalidJobTitle));
    }

    @Test
    public void isValidJobTitle() {
        // null job title
        assertThrows(NullPointerException.class, () -> JobTitle.isValidJobTitle(null));

        // invalid job title
        assertFalse(JobTitle.isValidJobTitle("")); // empty string
        assertFalse(JobTitle.isValidJobTitle(" ")); // spaces only
        assertFalse(JobTitle.isValidJobTitle("^")); // only non-alphanumeric characters
        assertFalse(JobTitle.isValidJobTitle("Cleaner*")); // contains non-alphanumeric characters

        // valid job title
        assertTrue(JobTitle.isValidJobTitle("cleaner")); // alphabets only
        assertTrue(JobTitle.isValidJobTitle("Junior Developer 2")); // alphanumeric characters
        assertTrue(JobTitle.isValidJobTitle("Account Manager")); // with capital letters
        assertTrue(JobTitle.isValidJobTitle("Senior Developer outsourced to GovTech as PM")); // long job titles
    }

    @Test
    public void equals() {
        JobTitle jobTitle = new JobTitle(VALID_JOB_TITLE_AMY);

        // same values -> returns true
        JobTitle toCopy = new JobTitle(VALID_JOB_TITLE_AMY);
        assertTrue(jobTitle.equals(toCopy));

        // same object -> returns true
        assertTrue(jobTitle.equals(jobTitle));

        // null -> returns false
        assertFalse(jobTitle.equals(null));

        // different type -> returns false
        assertFalse(jobTitle.equals(5));

        // different JobTitle -> returns false
        JobTitle different = new JobTitle(VALID_JOB_TITLE_BOB);
        assertFalse(jobTitle.equals(different));

    }
}

