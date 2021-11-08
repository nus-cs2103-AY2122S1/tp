package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.EventTask;
import seedu.address.testutil.EventTaskBuilder;

public class AddEventTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        CommandTestUtil.ModelStubAcceptingTaskAdded modelStub =
                new CommandTestUtil.ModelStubAcceptingTaskAdded();
        EventTask validTask = new EventTaskBuilder().build();

        CommandResult commandResult = new AddEventTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddEventTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        EventTask validTask = new EventTaskBuilder().build();
        AddEventTaskCommand addTaskCommand = new AddEventTaskCommand(validTask);
        CommandTestUtil.ModelStub modelStub =
                new CommandTestUtil.ModelStubWithTask(validTask);

        assertThrows(CommandException.class,
                AddEventTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addTaskCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        EventTask alice = new EventTaskBuilder().withName("Alice").build();
        EventTask bob = new EventTaskBuilder().withName("Bob").build();
        AddEventTaskCommand addAliceCommand = new AddEventTaskCommand(alice);
        AddEventTaskCommand addBobCommand = new AddEventTaskCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddEventTaskCommand addAliceCommandCopy = new AddEventTaskCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different task -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

}
