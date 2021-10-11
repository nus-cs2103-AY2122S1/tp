package seedu.fast.logic.commands;

import static seedu.fast.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fast.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.fast.model.Model;
import seedu.fast.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(""), model, expectedCommandResult, expectedModel);
    }
}
