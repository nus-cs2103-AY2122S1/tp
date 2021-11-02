package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {

        ExitCommand exitFirstCommand = new ExitCommand();
        ExitCommand exitSecondCommand = new ExitCommand();

        // same object -> returns true
        assertTrue(exitFirstCommand.equals(exitFirstCommand));

        // different types -> returns false
        assertFalse(exitFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exitFirstCommand.equals(null));

        // same -> returns true
        assertTrue(exitFirstCommand.equals(exitSecondCommand));
    }
}
