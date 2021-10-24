package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TaskListManagerTest {

    @Test
    public void equals() {
        TaskListManager firstManager = new TaskListManager();
        TaskListManager secondManager = new TaskListManager();

        // same object -> returns true
        assertEquals(firstManager, firstManager);

        // same values -> returns true
        assertEquals(firstManager, secondManager);

        // different types -> returns false
        assertFalse(firstManager.equals(1));

        // null -> returns false
        assertNotEquals(null, firstManager);
    }
}
