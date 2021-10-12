package seedu.address.model.person.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}

