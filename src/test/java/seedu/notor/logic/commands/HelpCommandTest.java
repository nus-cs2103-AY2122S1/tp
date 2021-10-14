package seedu.notor.logic.commands;

import static seedu.notor.logic.commands.CommandTestUtil.assertExecuteSuccess;
import static seedu.notor.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;

public class HelpCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, null, false);
        assertExecuteSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
