package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK_0;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_TASK_0);
        assertTrue(DESC_TASK_0.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_TASK_0.equals(DESC_TASK_0));

        // null -> returns false
        assertFalse(DESC_TASK_0.equals(null));

        // different types -> returns false
        assertFalse(DESC_TASK_0.equals(5));

        // different values -> returns false
        assertFalse(DESC_TASK_0.equals(DESC_TASK_1));

        // different taskName -> returns false
        EditTaskDescriptor editedTask0 = new EditTaskDescriptorBuilder(DESC_TASK_0)
                .withTaskName(VALID_TASK_NAME_1).build();
        assertFalse(DESC_TASK_0.equals(editedTask0));

        // different taskId -> returns false
        editedTask0 = new EditTaskDescriptorBuilder(DESC_TASK_0).withTaskId(VALID_TASK_ID_1).build();
        assertFalse(DESC_TASK_0.equals(editedTask0));

        // different taskDeadline -> returns false
        editedTask0 = new EditTaskDescriptorBuilder(DESC_TASK_0).withTaskDeadline(VALID_TASK_DEADLINE_1).build();
        assertFalse(DESC_TASK_0.equals(editedTask0));
    }
}
