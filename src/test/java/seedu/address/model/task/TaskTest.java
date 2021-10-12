package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.REPORT_1;
import static seedu.address.testutil.TypicalTasks.REPORT_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(REPORT_1.isSameTask(REPORT_1));

        // null -> returns false
        assertFalse(REPORT_1.isSameTask(null));

        // same name and deadline -> returns true
        Task editedReport1 = new TaskBuilder(REPORT_1).build();
        assertTrue(REPORT_1.isSameTask(editedReport1));

        // different name, deadline same -> returns false
        editedReport1 = new TaskBuilder(REPORT_2).withDeadline("2021-10-10").build();
        assertFalse(REPORT_1.isSameTask(editedReport1));

        // different deadline, name same -> returns false
        editedReport1 = new TaskBuilder(REPORT_1).withDeadline("2010-10-10").build();
        assertFalse(REPORT_1.isSameTask(editedReport1));

        // name differs in case, deadline same -> returns false
        String nameInLowerCase = REPORT_2.getName().toString().toLowerCase();
        Task editedReport2 = new TaskBuilder(REPORT_2).withName(nameInLowerCase).build();
        assertFalse(REPORT_2.isSameTask(editedReport2));

        // name has trailing spaces, deadline same -> returns false
        String nameWithTrailingSpaces = REPORT_2.getName().toString() + " ";
        editedReport2 = new TaskBuilder(REPORT_2).withName(nameWithTrailingSpaces).build();
        assertFalse(REPORT_2.isSameTask(editedReport2));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(REPORT_1, REPORT_1);

        // null -> returns false
        assertNotEquals(null, REPORT_1);

        // different type -> returns false
        assertNotEquals(5, REPORT_1);

        // different task -> returns false
        assertNotEquals(REPORT_1, REPORT_2);

        // different name -> returns false
        Task editedReport1 = new TaskBuilder(REPORT_1).withName("Random name").build();
        assertNotEquals(REPORT_1, editedReport1);

        // different deadline -> returns false
        editedReport1 = new TaskBuilder(REPORT_1).withDeadline("2010-12-12").build();
        assertNotEquals(REPORT_1, editedReport1);

        // different id -> returns false
        editedReport1 = new TaskBuilder(REPORT_1).withUniqueId("29b89cca-8a8b-43ab-8155-e839fb9711f0").build();
        assertNotEquals(REPORT_1, editedReport1);
    }
}
