package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.DeadlineTask;
import seedu.address.testutil.DeadlineTaskBuilder;

public class AddDeadlineTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDeadlineTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        CommandTestUtil.ModelStubAcceptingTaskAdded modelStub =
                new CommandTestUtil.ModelStubAcceptingTaskAdded();
        DeadlineTask validTask = new DeadlineTaskBuilder().build();

        CommandResult commandResult = new AddDeadlineTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddDeadlineTaskCommand.MESSAGE_SUCCESS, validTask),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        DeadlineTask validTask = new DeadlineTaskBuilder().build();
        AddDeadlineTaskCommand addTaskCommand = new AddDeadlineTaskCommand(validTask);
        CommandTestUtil.ModelStub modelStub =
                new CommandTestUtil.ModelStubWithTask(validTask);

        assertThrows(CommandException.class,
                AddDeadlineTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addTaskCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        DeadlineTask alice = new DeadlineTaskBuilder().withName("Alice").build();
        DeadlineTask bob = new DeadlineTaskBuilder().withName("Bob").build();
        AddDeadlineTaskCommand addAliceCommand = new AddDeadlineTaskCommand(alice);
        AddDeadlineTaskCommand addBobCommand = new AddDeadlineTaskCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddDeadlineTaskCommand addAliceCommandCopy = new AddDeadlineTaskCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different task -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

}
