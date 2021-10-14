package seedu.notor.logic.commands;

import static seedu.notor.logic.commands.CommandTestUtil.assertExecuteSuccess;
import static seedu.notor.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;

public class ExitCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, null, true);
        assertExecuteSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
