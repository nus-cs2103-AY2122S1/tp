package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.TodoTask;
import seedu.address.testutil.TodoTaskBuilder;

public class AddTodoTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTodoTaskCommand(null));
    }


    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        CommandTestUtil.ModelStubAcceptingTaskAdded modelStub =
                new CommandTestUtil.ModelStubAcceptingTaskAdded();
        TodoTask validTask = new TodoTaskBuilder().build();

        CommandResult commandResult = new AddTodoTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddTodoTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }


    @Test
    public void execute_duplicateTask_throwsCommandException() {
        TodoTask validTask = new TodoTaskBuilder().build();
        AddTodoTaskCommand addTodoTaskCommand = new AddTodoTaskCommand(validTask);
        CommandTestUtil.ModelStub modelStub = new CommandTestUtil.ModelStubWithTask(validTask);

        assertThrows(CommandException.class,
                AddTodoTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addTodoTaskCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        TodoTask todoIp = new TodoTaskBuilder().withName("Complete iP").build();
        TodoTask todoTp = new TodoTaskBuilder().withName("Complete tP").build();
        TodoTask todoTpFinalFeature = new TodoTaskBuilder().withName("Complete tP")
                .withDescription("Implement the final feature").build();
        AddTodoTaskCommand todoIpCommand = new AddTodoTaskCommand(todoIp);
        AddTodoTaskCommand todoTpCommand = new AddTodoTaskCommand(todoTp);
        AddTodoTaskCommand todoFinalFeature = new AddTodoTaskCommand(todoTpFinalFeature);

        // same object -> returns true
        assertTrue(todoIpCommand.equals(todoIpCommand));

        // same values -> returns true
        AddTodoTaskCommand addAliceCommandCopy = new AddTodoTaskCommand(todoIp);
        assertTrue(todoIpCommand.equals(addAliceCommandCopy));

        // tasks have different description -> returns false
        assertFalse(todoFinalFeature.equals(todoTpCommand));

        // different types -> returns false
        assertFalse(todoIpCommand.equals(1));

        // null -> returns false
        assertFalse(todoIpCommand.equals(null));

        // different task -> returns false
        assertFalse(todoIpCommand.equals(todoTpCommand));
    }

}
