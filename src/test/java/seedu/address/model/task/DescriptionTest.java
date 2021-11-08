package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.address.model.person.Name.isValidName(null));

        // invalid name
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("^")); // only non-alphanumeric characters
        assertFalse(Description.isValidDescription("Report *")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Description.isValidDescription("homework")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("cs2103 report")); // alphanumeric characters
        assertTrue(Description.isValidDescription("CS2103 Report")); // with capital letters
        assertTrue(Description.isValidDescription("A very very long task name")); // long names
    }

    @Test
    public void equals() {
        Description description1 = new Description("Report");
        Description description2 = new Description("Report");
        Description description3 = new Description("report");

        assertNotEquals(description1, description3);
        assertEquals(description1, description2);
    }
}
