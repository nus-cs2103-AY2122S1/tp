package dash.model.task;


import static dash.logic.commands.CommandTestUtil.VALID_TAG_UNGRADED;
import static dash.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.testutil.Assert;
import dash.testutil.TaskBuilder;
import dash.testutil.TypicalTasks;

public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task assignmentCopy = new TaskBuilder(TypicalTasks.ASSIGNMENT).build();
        assertTrue(TypicalTasks.ASSIGNMENT.equals(assignmentCopy));

        // same object -> returns true
        assertTrue(TypicalTasks.ASSIGNMENT.equals(TypicalTasks.ASSIGNMENT));

        // null -> returns false
        assertFalse(TypicalTasks.ASSIGNMENT.equals(null));

        // different type -> returns false
        assertFalse(TypicalTasks.ASSIGNMENT.equals(5));

        // different task -> returns false
        assertFalse(TypicalTasks.ASSIGNMENT.equals(TypicalTasks.QUIZ));

        // different task description -> returns false
        Task editedAssignment = new TaskBuilder(TypicalTasks.ASSIGNMENT).withTaskDescription(VALID_TASK_DESCRIPTION)
                .build();
        assertFalse(TypicalTasks.ASSIGNMENT.equals(editedAssignment));

        // different tags -> returns false
        editedAssignment = new TaskBuilder(TypicalTasks.ASSIGNMENT).withTags(VALID_TAG_UNGRADED).build();
        assertFalse(TypicalTasks.ASSIGNMENT.equals(editedAssignment));
    }


}
